package com.mywork.controller;

//课程排序控制层，主要的业务逻辑都在其中，主要负责于前端页面的交互
import java.util.*;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


import com.mywork.bean.*;
import com.mywork.service.*;
import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mywork.util.PagerUtil;
import com.mywork.util.QueryUtil;
/**
 * 
 * @author 
 *
 */
@Controller
@SuppressWarnings("unchecked")
@RequestMapping(value="timetable")//RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
public class TimetableController extends BaseController{//继承
	@Inject
	private TimetableService timetableService;
	@Inject
	private UserService userService;
	@Inject
	private MajorService majorService;
	@Inject
	private RoomService roomService;
	@Inject
	private TempService tempService;
	@Inject
	private DictService dictService;

	class Obj{
		//组合： 专业-课程-老师 5_马哲_41
		private String name;
		//时间
		private String time;

		public Obj(String name, String time) {
			this.name = name;
			this.time = time;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
	}

	private static Integer WEEKS=23;//周数

	private static Integer OneDay=4;//一天的课数


	/* 每个list的元素 都是 node  属性为 node index 和  描述信息（包含 课程 老师 专业）
	 *
	 * 有一个List<List>形式如：   0   1  2  3....    WEEKS     未被占用的     在需要占用的时候首先要确认 是否是连续的
	 *                         0   1  2  3...     WEEKS
	 *
	 * 有一个List<List>形式如：   0   1  2  3....    WEEKS     已经占用的
	 *                         0   1  2  3...     WEEKS
	 *                           占据的list中 还要有属性描述，  那些老师已经占据了哪些课程
	 *                      	 在空闲的list占用的时候 需要去掉老师的相应时间
	 */
	@RequestMapping(value="auto")
	public ModelAndView auto2(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();

		List<Obj> priority = new ArrayList<>();
		List<Obj> follow = new ArrayList<>();

		//删除原有排课
		timetableService.deleteall("");

		                                                       /*组合 专业-课程-老师 5_马哲_41*/
		map.put("type", "major");
		List<Major> majorlist = majorService.getList(map);
		//获取名称 时间 的map
		Map<String, String> collect=new HashMap<>();
		for (Dict dict : dictService.getList(null)) {
			collect.put(dict.getType(),dict.getTime());
		}
		for(int i=0; i<majorlist.size(); i++){//循环专业
			System.err.println("================="+i);
			Major major = majorlist.get(i);
			if(major.getClasses()!= null && !"".equals(major.getClasses())){//判断该专业是否有课程
				String[] classarr = major.getClasses().split(",");
				for(String classes : classarr){
					map.put("classes", classes);
					map.put("ranorder", "ranorder");
					User user = userService.getList(map).get(0);
					String time = collect.get(classes);
					if(time.contains("*")){
						priority.add(new Obj(major.getId()+"_"+classes+"_"+user.getId(),time));//以*标示priority（有开始和结束周限制的课程）
					}else{
						follow.add(new Obj(major.getId()+"_"+classes+"_"+user.getId(),time));//没有开始周和结束周限制的课程
					}
				}
			}
		}


		
		Collections.shuffle(priority);//两种课程的list集合打散
		Collections.shuffle(follow);


		List<Room> roomlist = roomService.getList(map);//教室集合
		List<List<List<Obj>>> free = init(roomlist.size());//初始化一个三维list集合：一维是一共有几个教室、二维是每周有多少节课、第三维是哪些周还可以被安排
		//key: room_i_j    value:teacherId
		Map<String, String> occDesc = new HashMap<>();//记录老师上课信息、包括room_i_j：哪一周和哪一节课，teacherId：老师在数据库中的id
		//找出当前最合适的时间（找出第一个即可返回）

		String index="";
		//首先对具有时间限制的排课进行处理
		for (Obj obj : priority) {
			int min=Integer.MAX_VALUE;
			String[] split = obj.getTime().split("\\*");
			//确定了开始时间split[0]和结束时间split[1] 然后范围内搜索最合适的
			for (int i = 0; i < free.size(); i++) {
				for (int j = 0; j < free.get(i).size(); j++) {
					List<Obj> objList = free.get(i).get(j);
//					compare函数：判断是否能占用这个时间段（这个时间还能排的周数大于这门课的周数）；objList.size()<=min：判断是否是最节省的
					if(compare(objList,split[0],split[1],j,roomlist,roomlist.get(i).getId(), occDesc,obj.getName().split("_")[2]) && objList.size()<=min){
						index=i+"_"+j;//index i j：表示i是哪一周 j是第几节课
						min=objList.size(); //如果能占用、判断这个时间段是不是确保最少算法
					}
				}
			}
			//已经找到了合适的教师 教师 时间， 进行占据
			String[] sp = index.split("_");
			List<Obj> list = free.get(Integer.valueOf(sp[0])).get(Integer.valueOf(sp[1]));// 哪些周可以被这门课占据
			//迭代器：取出list里面的对象
			Iterator<Obj> iterator = list.iterator();
			for(Iterator<Obj> it=list.iterator(); it.hasNext();){
				Obj c = it.next();
				Integer cTime = Integer.valueOf(c.getTime());
				Integer start = Integer.valueOf(split[0]);
				Integer end = Integer.valueOf(split[1]);
				if(cTime>=start-1 && cTime<=end-1){
					it.remove();//删去被占据的时间段
					occDesc.put(roomlist.get(Integer.valueOf(sp[0])).getId()+"_"+sp[1]+"_"+cTime,obj.getName());//记录被哪个老师占据
				}
				if(cTime>end-1){
					break;
				}
			}
			free.get(Integer.valueOf(sp[0])).set(Integer.valueOf(sp[1]),list);// 把已经排好课的时间段放入三维list中（更新三维list）

		}
		//对具没有时间限制的排课进行处理
		int st=0;
		for (Obj obj : follow) {
			int min=Integer.MAX_VALUE;
			String time = obj.getTime();
			for (int i = 0; i < free.size(); i++) {
				for (int j = 0; j < free.get(i).size(); j++) {
					List<Obj> objList = free.get(i).get(j);
					Pair<Integer, Integer> pair = compare(objList, time, j, roomlist, roomlist.get(i).getId(),
							occDesc, obj.getName().split("_")[2]);
					if(pair.getKey()>=Integer.valueOf(obj.getTime()) && pair.getKey()<=min){
						index=i+"_"+j;
						min=objList.size();
						st=pair.getValue();
					}
				}
			}
			//已经找到了合适的教师 教师 时间， 进行占据
			String[] sp = index.split("_");
			List<Obj> list = free.get(Integer.valueOf(sp[0])).get(Integer.valueOf(sp[1]));
			Integer start = Integer.valueOf(list.get(st).getTime());
			Integer end = Integer.valueOf(list.get(st+Integer.valueOf(time)-1).getTime());
			Iterator<Obj> iterator = list.iterator();
			for(Iterator<Obj> it=list.iterator(); it.hasNext();){
				Obj c = it.next();
				Integer cTime = Integer.valueOf(c.getTime());
				if(cTime>=start && cTime<=end){
					it.remove();
					occDesc.put(roomlist.get(Integer.valueOf(sp[0])).getId()+"_"+sp[1]+"_"+cTime,obj.getName());
				}
				if(cTime>end){
					break;
				}
			}
			free.get(Integer.valueOf(sp[0])).set(Integer.valueOf(sp[1]),list);
		}
		//整理输出
		Map<String, ArrayList<String>> resultMap = new HashMap<>();
		for (Map.Entry<String, String> entry : occDesc.entrySet()) {
			if(resultMap.containsKey(entry.getValue())){
				ArrayList<String> list = resultMap.get(entry.getValue());
				list.add(entry.getKey());
				resultMap.put(entry.getValue(),list);
			}else{
				ArrayList<String> list = new ArrayList<>();
				list.add(entry.getKey());
				resultMap.put(entry.getValue(),list);
			}
		}
		Map<Integer, Major> majorMap = new HashMap<>();
		for (Major major : majorlist) {
			majorMap.put(major.getId(),major);
		}
		Map<Integer, Room> roomMap = new HashMap<>();
		for (Room room : roomlist) {
			roomMap.put(room.getId(),room);
		}
		List<Timetable> timetableList = new ArrayList<>();
		for (Map.Entry<String, ArrayList<String>> entry : resultMap.entrySet()) {
			Timetable timetable = new Timetable();
			timetable.setMajor(majorMap.get(Integer.valueOf(entry.getKey().split("_")[0])));
			timetable.setMajorid(Integer.valueOf(entry.getKey().split("_")[0]));
			timetable.setRoom(roomMap.get(Integer.valueOf(entry.getValue().get(0).split("_")[0])));
			timetable.setRoomid(Integer.valueOf(entry.getValue().get(0).split("_")[0]));
			timetable.setClasses(entry.getKey().split("_")[1]);
			timetable.setTeacher(userService.getUserById(entry.getKey().split("_")[2]));
			timetable.setTeacherid(Integer.valueOf(entry.getKey().split("_")[2]));

			ArrayList<String> value = entry.getValue();
			value.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return Integer.valueOf(o1.split("_")[2])-Integer.valueOf(o2.split("_")[2]);
				}
			});

			int w = Integer.valueOf(value.get(0).split("_")[1]) / OneDay + 1;
			String we="";
			switch(w){
				case 1:
					we="周一";
				case 2:
					we="周二";
				case 3:
					we="周三";
				case 4:
					we="周四";
				case 5:
					we="周五";
			}

			timetable.setTime(Integer.valueOf(value.get(0).split("_")[1])%OneDay +1 +"");
			timetable.setWeek(we);
			timetable.setStart(Integer.valueOf(value.get(0).split("_")[2])+1);
			timetable.setEnd(Integer.valueOf(value.get(0).split("_")[2])+value.size());
			timetableList.add(timetable);
		}
		//离散化

		//插入到数据库
		for (Timetable timetable : timetableList) {
			timetableService.insert(timetable);
		}
		//分页展示
		String pagerNum = request.getParameter("pagerNum");
		if(pagerNum==null){
			pagerNum = "1";
		}

		map.put("count", timetableList.size());
		map.put("maxPager", timetableList.size()/PagerUtil.getPagerSize()+1);
		timetableList = (List<Timetable>) PagerUtil.getPager(timetableList, Integer.parseInt(pagerNum));
		map.put("pagerNum", pagerNum);
		map.put("list", timetableList);
		return jsp("timetable/list", map, request);

	}

	//找出最小连续子序列的长度以及起始位置 最小损失
	private Pair<Integer,Integer> compare(List<Obj> objList, String time, int index, List<Room> roomlist,//没有限制开始结束周课程的compare
										  int roomId, Map<String, String> map, String teacherId) {
		ArrayList<Integer> nums = new ArrayList<>();
		for (int i = 0; i < objList.size(); i++) {
			Integer c = Integer.valueOf(objList.get(i).getTime());
			if(judge(index,c,roomlist,roomId,map,teacherId)){
				nums.add(c);
			}
		}
		Integer k = Integer.valueOf(time);
		int res=0;
		int len=1;
		int end=nums.size()-1;
		for (int i = 1; i < nums.size(); i++) {
			if(nums.get(i)==nums.get(i-1)+1){
				len++;
			}
			else {
				len=1;

			}
			if(res<len){
				res=len;
				end=i;
			}
		}
		System.out.println(res);
		System.out.println(end-res+1);
		if(res>=k){
			return new Pair<Integer, Integer>(res,end-res+1);
		}else{
			return new Pair<Integer, Integer>(-1,-1);
		}

	}

	//有限制开始结束周课程的compare
	private boolean compare(List<Obj> objList, String begin, String end,int i,List<Room> roomlist,
							int roomId,Map<String, String> map,String teacherId){//i：第几节课 map：老师占据的时间段描述

		int num=0;
		int index=0;
		Integer op = Integer.valueOf(begin);
		Integer ed = Integer.valueOf(end);
		for (Obj obj : objList) {
			int time=Integer.valueOf(obj.getTime());
			if(time>=op-1&& time<=ed-1 &&judge(i,time,roomlist,roomId,map,teacherId)){//判断这个空闲周、是否在开始周和结束周之间 &&这个时间段 该老师是否在别的教室上课
				num++;
			}
			if(Integer.valueOf(obj.getTime())>ed-1){
				break;
			}
		}
		if(num==(ed-op+1)){
			return true;
		}
		return false;
	}

	private  List<List<List<Obj>>> init(int size){
		List<List<List<Obj>>> free = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			List<List<Obj>> rList = new ArrayList<>();
			for (int j = 0; j < OneDay * 5; j++) {
				List<Obj> oList = new ArrayList<>();
				for (int q = 0; q < WEEKS; q++) {
					oList.add(new Obj("",q+""));
				}
				rList.add(oList);
			}
			free.add(rList);
		}
		return free;
	}
	//同一时刻的其他房间 该老师不在授课
	private boolean judge(int i, int j,List<Room> roomlist,int roomId,Map<String, String> map,String teacherId){
		ArrayList<Integer> list = new ArrayList<>();
		for (Room room : roomlist) {
			if(room.getId()!=roomId){
				list.add(room.getId());
			}
		}
		for (Integer temp : list) {
			if(teacherId.equals(map.get(temp+"_"+i+"_"+j)==null?null:map.get(temp+"_"+i+"_"+j).split("_")[2])){
				return false;
			}

		}
		return true;
	}


	//	列表
	//先把专业课程老师三个限制条件组合在一起，再把组合体塞到教室
	@RequestMapping(value="auto2")
	public ModelAndView auto(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> infolist = new ArrayList<String>();//目前已经记录的排课信息
		//删除排课
		tempService.delete("");
		//组合 专业-课程-老师
		map.put("type", "major");
		List<Major> majorlist = majorService.getList(map);//把专业查出来
		for(int i=0; i<majorlist.size(); i++){//循环专业，对每个专业下面的每个课程，随机找一个相应课程类型的老师去教
			System.err.println("================="+i);
			Major major = majorlist.get(i);//取出专业
			if(major.getClasses()!= null && !"".equals(major.getClasses())){//判断该专业是否有课程
				String[] classarr = major.getClasses().split(",");// 将课程的字符组合拆分为每个课程
				for(String classes : classarr){
					map.put("classes", classes);
					map.put("ranorder", "ranorder");
					User user = userService.getList(map).get(0);//随机找一个相应课程类型的老师去教
					//拼接字符串并放入课程list
					infolist.add(major.getId()+"_"+classes+"_"+user.getId());//专业、课程、教师id拼接组合起来放到ifolist里面
				}
			}
		}
		Collections.shuffle(infolist);//课程打乱顺序
		List<Room> roomlist = roomService.getList(map);// 查询教室list
		if(infolist.size() > (roomlist.size()*20)){//课程总的数量是否大于教室每周的排课量
			map.put("msg", "课程总量大于所有教室每周排课量，排课失败");
		}else{
			for(int i=0;i<roomlist.size(); i++){//教室循环（取每个教室时对应的infolist的id）
				int startlessonsize = i*20;
				int endlessonsize = (i+1)*20;
				int max = endlessonsize;
				if(endlessonsize >= infolist.size()){
					endlessonsize = infolist.size();//判断课程是不是最后一批
				}
				//把已经排好序课程存到数据库
				String sql = "insert into temp(roomid,a1,a2,a3,a4,b1,b2,b3,b4,c1,c2,c3,c4,d1,d2,d3,d4,e1,e2,e3,e4) values ("+roomlist.get(i).getId();//把roomlist里面的信息与roomid
				String values = "";
				for(int j=startlessonsize; j<max; j++){// 把infolist里面的信息以20个为单位（每间教室每周能排20节）与每个room对象的id组合起来 放入到temp的 a1-e4
					if(j<endlessonsize){
						//判断是否重复
						
						values += ",'"+infolist.get(j)+"'";
					}else{
						values += ",''";
					}
				}
				sql += values+")";
				QueryUtil.executeSql(sql);
			}
			//把temp插入课程表
			//先清空
			timetableService.deleteall("");
			List<Temp> templist = tempService.getList(map);
			Temp basetemp = templist.get(0);
//			把组合信息进行拆分、插入到课程表中
			for(int i=1; i<templist.size(); i++){
				Temp temp = templist.get(i);
				int roomid = temp.getRoomid();
				String v1 = temp.getA1();
				if(v1 != null && !"".equals(v1)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v1.split("_")[0]));
					timetable.setClasses(v1.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v1.split("_")[2]));
					timetable.setWeek(basetemp.getA1().split("_")[0]);
					timetable.setTime(basetemp.getA1().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v2 = temp.getA2();
				if(v2 != null && !"".equals(v2)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v2.split("_")[0]));
					timetable.setClasses(v2.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v2.split("_")[2]));
					timetable.setWeek(basetemp.getA2().split("_")[0]);
					timetable.setTime(basetemp.getA2().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v3 = temp.getA3();
				if(v3 != null && !"".equals(v3)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v3.split("_")[0]));
					timetable.setClasses(v3.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v3.split("_")[2]));
					timetable.setWeek(basetemp.getA3().split("_")[0]);
					timetable.setTime(basetemp.getA3().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v4 = temp.getA4();
				if(v4 != null && !"".equals(v4)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v4.split("_")[0]));
					timetable.setClasses(v4.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v4.split("_")[2]));
					timetable.setWeek(basetemp.getA4().split("_")[0]);
					timetable.setTime(basetemp.getA4().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v5 = temp.getB1();
				if(v5 != null && !"".equals(v5)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v5.split("_")[0]));
					timetable.setClasses(v5.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v5.split("_")[2]));
					timetable.setWeek(basetemp.getB1().split("_")[0]);
					timetable.setTime(basetemp.getB1().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v6 = temp.getB2();
				if(v6 != null && !"".equals(v6)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v6.split("_")[0]));
					timetable.setClasses(v6.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v6.split("_")[2]));
					timetable.setWeek(basetemp.getB2().split("_")[0]);
					timetable.setTime(basetemp.getB2().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v7 = temp.getB3();
				if(v7 != null && !"".equals(v7)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v7.split("_")[0]));
					timetable.setClasses(v7.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v7.split("_")[2]));
					timetable.setWeek(basetemp.getB3().split("_")[0]);
					timetable.setTime(basetemp.getB3().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v8 = temp.getB4();
				if(v8 != null && !"".equals(v8)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v8.split("_")[0]));
					timetable.setClasses(v8.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v8.split("_")[2]));
					timetable.setWeek(basetemp.getB4().split("_")[0]);
					timetable.setTime(basetemp.getB4().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v9 = temp.getC1();
				if(v9 != null && !"".equals(v9)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v9.split("_")[0]));
					timetable.setClasses(v9.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v9.split("_")[2]));
					timetable.setWeek(basetemp.getC1().split("_")[0]);
					timetable.setTime(basetemp.getC1().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v10 = temp.getC2();
				if(v10 != null && !"".equals(v10)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v10.split("_")[0]));
					timetable.setClasses(v10.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v10.split("_")[2]));
					timetable.setWeek(basetemp.getC2().split("_")[0]);
					timetable.setTime(basetemp.getC2().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v11 = temp.getC3();
				if(v11 != null && !"".equals(v11)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v11.split("_")[0]));
					timetable.setClasses(v11.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v11.split("_")[2]));
					timetable.setWeek(basetemp.getC3().split("_")[0]);
					timetable.setTime(basetemp.getC3().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v12 = temp.getC4();
				if(v12 != null && !"".equals(v12)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v12.split("_")[0]));
					timetable.setClasses(v12.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v12.split("_")[2]));
					timetable.setWeek(basetemp.getC4().split("_")[0]);
					timetable.setTime(basetemp.getC4().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v13 = temp.getD1();
				if(v13 != null && !"".equals(v13)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v13.split("_")[0]));
					timetable.setClasses(v13.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v13.split("_")[2]));
					timetable.setWeek(basetemp.getD1().split("_")[0]);
					timetable.setTime(basetemp.getD1().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v14 = temp.getD2();
				if(v14 != null && !"".equals(v14)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v14.split("_")[0]));
					timetable.setClasses(v14.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v14.split("_")[2]));
					timetable.setWeek(basetemp.getD2().split("_")[0]);
					timetable.setTime(basetemp.getD2().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v15 = temp.getD3();
				if(v15 != null && !"".equals(v15)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v15.split("_")[0]));
					timetable.setClasses(v15.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v15.split("_")[2]));
					timetable.setWeek(basetemp.getD3().split("_")[0]);
					timetable.setTime(basetemp.getD3().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v16 = temp.getD4();
				if(v16 != null && !"".equals(v16)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v16.split("_")[0]));
					timetable.setClasses(v16.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v16.split("_")[2]));
					timetable.setWeek(basetemp.getD4().split("_")[0]);
					timetable.setTime(basetemp.getD4().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v17 = temp.getE1();
				if(v17 != null && !"".equals(v17)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v17.split("_")[0]));
					timetable.setClasses(v17.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v17.split("_")[2]));
					timetable.setWeek(basetemp.getE1().split("_")[0]);
					timetable.setTime(basetemp.getE1().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v18 = temp.getE2();
				if(v18 != null && !"".equals(v18)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v18.split("_")[0]));
					timetable.setClasses(v18.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v18.split("_")[2]));
					timetable.setWeek(basetemp.getE2().split("_")[0]);
					timetable.setTime(basetemp.getE2().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v19 = temp.getE3();
				if(v19 != null && !"".equals(v19)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v19.split("_")[0]));
					timetable.setClasses(v19.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v19.split("_")[2]));
					timetable.setWeek(basetemp.getE3().split("_")[0]);
					timetable.setTime(basetemp.getE3().split("_")[1]);
					timetableService.insert(timetable);
				}
				String v20 = temp.getE4();
				if(v20 != null && !"".equals(v20)){
					//专业-课程-老师
					Timetable timetable = new Timetable();
					timetable.setRoomid(roomid);
					timetable.setMajorid(Integer.parseInt(v20.split("_")[0]));
					timetable.setClasses(v20.split("_")[1]);
					timetable.setTeacherid(Integer.parseInt(v20.split("_")[2]));
					timetable.setWeek(basetemp.getE4().split("_")[0]);
					timetable.setTime(basetemp.getE4().split("_")[1]);
					timetableService.insert(timetable);
				}
			}
			map.clear();
			map.put("msg", "智能排课成功！");
		}
		
		//分页展示
		String pagerNum = request.getParameter("pagerNum");
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<Timetable> list = timetableService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<Timetable>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		return jsp("timetable/list", map, request);
	}

	//	排课查询、展示排课结果
	@RequestMapping(value="checklist")
	public ModelAndView checklist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();//map存放查询结果
		map.put("roomlist", roomService.getList(map));
		map.put("type", "major");
		List<Major> majorlist = majorService.getList(map);
		map.put("majorlist", majorlist);
		map.put("isadmin", 1);
		map.put("teacherlist", userService.getList(map));
		String pagerNum = request.getParameter("pagerNum");
		if(pagerNum==null || "".equals(pagerNum)){// 分页
			pagerNum = "1";
		}
		map.put("roomid", request.getParameter("roomid"));
		map.put("teacherid", request.getParameter("teacherid"));
		map.put("majorid", request.getParameter("majorid"));
		map.put("classes", request.getParameter("classes"));
		List<Timetable> list = timetableService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<Timetable>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));//分页
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		return jsp("timetable/checklist", map, request);
	}

	@RequestMapping(value="list")//	列表查询到页面 分页
	public ModelAndView list(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String pagerNum = request.getParameter("pagerNum");
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<Timetable> list = timetableService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<Timetable>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		return jsp("timetable/list", map, request);
	}

	//	时间表查询集合  根据map要求 教师
	@RequestMapping(value="tealist")
	public ModelAndView tealist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("teacherid", getSessionUser(request).getId());
		List<Timetable> list = timetableService.getList(map);
		map.put("list", list);
		return jsp("timetable/stulist", map, request);
	}

	//	时间表查询集合  根据map要求 学生
	@RequestMapping(value="stulist")
	public ModelAndView stulist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("majorid", getSessionUser(request).getMajor());
		List<Timetable> list = timetableService.getList(map);
		map.put("list", list);
		return jsp("timetable/stulist", map, request);
	}

	//	考试时间列表 老师
	@RequestMapping(value="teaexamlist")
	public ModelAndView teaexamlist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("teacherid", getSessionUser(request).getId());
		List<Timetable> list = timetableService.getList(map);
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("list", list);
		return jsp("timetable/examlist", map, request);
	}


	//	 考试时间列表 学生
	@RequestMapping(value="stuexamlist")
	public ModelAndView stuexamlist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("majorid", getSessionUser(request).getMajor());
		List<Timetable> list = timetableService.getList(map);
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("list", list);
		return jsp("timetable/examlist", map, request);
	}


//                                        手动排课
//	到添加页面
	@RequestMapping(value="toadd")
	public ModelAndView toadd(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		//院系专业
		map.put("type", "college");
		map.put("collegelist", majorService.getList(map));
		map.put("majorlist", majorService.getList(map));
		map.put("type", "college");
		map.put("roomlist", roomService.getList(map));
		return jsp("timetable/add", map, request);
	}


	//	添加到数据库
	@RequestMapping(value="add")
	public ModelAndView add(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();

		String majorid = request.getParameter("majorid");
		String classes = request.getParameter("classes");
		String teacherid = request.getParameter("teacherid");
		String roomid = request.getParameter("roomid");
		String week = request.getParameter("week");
		String time = request.getParameter("time");
//TODO 1判断老师改week，time时是否有课 2判断教师在week，time时是否被占用
		map.put("teacherid", teacherid);
		map.put("week", week);
		map.put("time", time);
		List<Timetable> list1 = timetableService.getList(map);
		if(list1.size() >0){
			map.put("msg", "该教师时间冲突，请重新选择");
			//院系专业
			map.put("type", "major");
			map.put("majorlist", majorService.getList(map));
			map.put("type", "college");
			map.put("collegelist", majorService.getList(map));
			map.put("roomlist", roomService.getList(map));
			return jsp("timetable/add", map, request);
		}
		map.clear();
		map.put("roomid", roomid);
		map.put("week", week);
		map.put("time", time);
		List<Timetable> list2 = timetableService.getList(map);
		if(list2.size() >0){
			map.put("msg", "该教室时间冲突，请重新选择");
			//院系专业
			map.put("type", "major");
			map.put("majorlist", majorService.getList(map));
			map.put("type", "college");
			map.put("collegelist", majorService.getList(map));
			map.put("roomlist", roomService.getList(map));
			return jsp("timetable/add", map, request);
		}

		Timetable timetable = new Timetable();
		timetable.setMajorid(Integer.parseInt(majorid));
		timetable.setClasses(classes);
		timetable.setTeacherid(Integer.parseInt(teacherid));
		timetable.setRoomid(Integer.parseInt(roomid));
		timetable.setWeek(week);
		timetable.setTime(time);
		map.clear();
		boolean issuc = timetableService.insert(timetable);
		if(issuc){
			map.put("msg", "新增成功");
		}else{
			map.put("msg", "新增失败，请重新操作");
		}

		String pagerNum = request.getParameter("pagerNum");
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<Timetable> list = timetableService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<Timetable>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		return jsp("timetable/list", map, request);
	}



	//	删除
	@RequestMapping(value="del")
	public ModelAndView del(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();

		String id = request.getParameter("id");

		boolean issuc = timetableService.delete(id);
		if(issuc){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败，请重新操作");
		}
		String pagerNum = request.getParameter("pagerNum");
		if(pagerNum==null){
			pagerNum = "1";
		}
		List<Timetable> list = timetableService.getList(map);
		map.put("count", list.size());
		map.put("maxPager", list.size()/PagerUtil.getPagerSize()+1);
		list = (List<Timetable>) PagerUtil.getPager(list, Integer.parseInt(pagerNum));
		for(int i=0; i<list.size(); i++){
			list.get(i).setMajor(majorService.getMajorById(list.get(i).getMajorid()+""));
			list.get(i).setTeacher(userService.getUserById(list.get(i).getTeacherid()+""));
			list.get(i).setRoom(roomService.getRoomById(list.get(i).getRoomid()+""));
		}
		map.put("pagerNum", pagerNum);
		map.put("list", list);
		return jsp("timetable/list", map, request);

	}

}
