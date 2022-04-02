Fai.top = window;
var bgmCloseToOpen = false;
window.onerror = function() {
	if (typeof Site == 'undefined') {
		alert('您的网页未加载完成，请尝试按“CTRL+功能键F5”重新加载。');
	}
};
var _colOtherStyleData = {"y":0,"h":0,"independentList":[],"layout4Width":0};						// 当前页面的数据    
var _templateOtherStyleData = {"y":0,"h":612,"independentList":[],"layout4Width":0};						// 全局的数据   
$(function() {
	if(false){
		Fai.ing("",true);
	}
	//topBarMember 
	
	
	
	// 管理态下, QQ/微博登陆 禁止登陆
	if( _manageMode ) {
		$('#memberBar .l_Btn').click(function(){
			Fai.ing('您目前处于网站管理状态，请先点击网站右上方的“退出”后再登录会员。', true);
		});
		//绑定放大镜遮罩效果事件
		Site.bindEventToOverLayer();
		//浮动设置面板
		
		
	}
	// 绑定退出事件
	$(window).bind("beforeunload", function(e) { 
			if(bgmCloseToOpen){
				Site.bgmFlushContinue();
			}	
	});
	

	Site.initTemplateLayout(1);
	// ajax统计
	Site.total({colId:2, pdId:-1, ndId:-1, sc:0, rf:"http://www.faisco.com/model.html?k=36"});
	
	Site.initBanner({"_open":true,"data":[{"title":"","desc":"","imgWidth":960,"imgHeight":330,"src":"/bpq/resources/images/76.gif"}],"width":960,"height":330,"playTime":4000,"animateTime":1500,"from":"banner","btnType":1}, {"_open":false}, 4);
	Site.loadProductPicList(2, false, true);
	Site.initModuleProductSearch(4);
	Site.initModuleProductFilter(304, false);
	Site.loadProductMarquee(303, true, false, 1);
	Fai.top.changeMarquee303 = function(){Fai.stopInterval('marquee303');Site.loadProductMarquee(303, true, false, 1);};
	Site.loadNewsList(3);
	Site.initPage();	// 这个要放在最后，因为模块组初始化时会把一些模块隐藏，导致没有高度，所以要放最后执行
	setTimeout("afterModuleLoaded()", 0);
	if (!_oem && _manageMode) {
		Site.siteGuideInit();
	}
	
});

function afterModuleLoaded() {
	Site.initPage2();


} // afterModuleLoaded end

var _portalHost = 'www.faisco.cn';

var _lcid = 2052;
var _signupDays = 1130;
var _cid = 4723;
var _colId = 2;
var _extId = 0;
var _fromColId = -1;
var _designAuth = false;
var _manageMode = false;
var _oem = false;
var _siteAuth = 0;
var _adm = false;
var _siteVer = 40;

var nav2SubMenu=[];
var nav3SubMenu=[];
var nav4SubMenu=[];
var nav5SubMenu=[];
var nav6SubMenu=[];
var nav9SubMenu=[];
var moduleAttr={"module2":{"pattern":{"mulModuleColPattern":{"mcs":2},"searchBoxPattern":{"iw":-1}}},"module4":{"pattern":{"mulModuleColPattern":{"mcs":-1},"systemSearchBoxStyle":8,"searchBoxPattern":{"iw":110}}},"module304":{"pattern":{"mulModuleColPattern":{"mcs":-1},"searchBoxPattern":{"iw":-1}}},"module7":{"pattern":{"mulModuleColPattern":{"mcs":0},"searchBoxPattern":{"iw":-1}}},"module301":{"pattern":{"mulModuleColPattern":{"mcs":1},"searchBoxPattern":{"iw":-1}}},"module303":{"pattern":{"mulModuleColPattern":{"mcs":12},"searchBoxPattern":{"iw":-1}}},"module3":{"pattern":{"mulModuleColPattern":{"mcs":8},"searchBoxPattern":{"iw":-1}}},"module6":{"pattern":{"mulModuleColPattern":{"mcs":0},"searchBoxPattern":{"iw":-1}}}};
var _frameChanged = 0;//用于记录管理frame中的页面数据是否有保存，以便在管理frame关闭时刷新页面
var _changeStyleNum = 0;//用于记录网站风格是否更改，如果更改则在刷新或者关闭前进行提醒。
var _templateChanged = 0; //用于记录模版是否有改动
var _moduleMoved = 0; //用于记录模块是否有移动
var _layoutChanged = 0; //用于布局是否有改动
var _titleChanged = 0; //用于记录企业标题是否更改，如果是使用模板的初始化，则是-1；如果人工修改过则>0
var _localeChanged = 0; //用于记录企业版本标题是否更改
var _logoChanged = 0; //用于记录logo是否有更改
var _bgImgChanged = 0;//记录页面背景是否有更改
var _bannerPath = "";//记录上传banner前的原banner路径，用于恢复原始banner
var _bannerChanged = 0;//用于记录banner是否更改
var _searchKeywordChanged = 0; //用于记录searchKeyword是否更改
var _searchDescChanged = 0;//用于记录searchDesc是否更改
//var _tmpDataList = null;//new Array(); //临时json，用于记录popup的iframe页面刷新前用户输入的数据
var _templateFrame = 307;
var _templateLayout = 1;
var _templateType = 0;
var _templateBannerId = 1723;
var _templateBannerWeight = 12;
var _moduleList = new Array();//用于记录哪些模块的titleHidden值改变了
var _floatOutPosition = "centerTopForms";
var _browserTitleChanged = 0; //用于记录浏览器标题是否改变
var _appendLayout = 2;	// 用于记录添加新模块时加入的区域
var _appendModule = -1;	// 用于记录添加新模块时插入的模块
/*
var _templateChangeLayoutList = {
	left:true, 
	right:false
};	// 在切换不同布局的模版时，左右是否需要显示，如果是用户主动隐藏的，切换时就不显示了，否则就要显示。
*/
var _displayLayoutList = [1,2];
var _newModuleIdList = [];

var _imgPageChanged = 0;
var _imgPage = {"s":true,"b":false,"img":false,"e":false,"n":1,"w":960,"g":600,"iw":960,"ih":600,"r":-1,"c":"","p":"","ii":"","ipath":"","itpath":"","i":"","path":"","fb":false,"fr":-1,"fc":"","fp":"","ib":false,"ir":-1,"ic":"","ip":""};
var _imgPageOpen = false;
var _bgMusicChanged = 0;
var _bgMusicOpen = false;
var _bgMusic = {"o":true,"l":true,"i":"","p":""};
var _hiddenModuleList = [];

var _useTemplateBanner = true;				// 是否使用全局模版
var _templateBannerData = {"n":[{"i":"76","w":960,"h":330,"t":0,"p":"http://0.ss.faidns.com/image/template/banners/76.gif","e":0,"u":""}],"ce":{},"s":4,"i":4000,"a":1500,"h":false,"o":false,"t":1,"p":0,"pt":0,"pl":0,"bt":1,"l":[],"f":{},"c":3};		// 模版的数据
var _pageBannerData = {"s":0,"i":4000,"a":1500,"h":false,"o":false,"t":1,"p":0,"pt":0,"pl":0,"bt":1,"l":[],"f":{},"ce":{},"n":[],"c":3};					// 当前页面的自定义数据（页面独立样式设置）
var _bannerData = _templateBannerData;						// 当前页面的数据

var _navStyleChanged = 0;
var _navItemOnCkOpen = false;					  // 有下级菜单不可点击
var _navStyleData = {"v":0,"no":true,"s":0,"ns":{"w":-1,"h":-1},"cs":{"w":-1,"h":-1},"gt":{"y":0},"ht":{"y":0},"np":{},"nb":{"y":0},"cb":{"y":0},"ncp":{"y":0,"t":-1,"r":-1,"b":-1,"l":-1},"cp":{"y":0,"t":-1,"l":-1},"nis":{"w":-1,"h":-1},"nib":{"y":0},"nihb":{"y":0},"niss":{"w":-1,"h":-1},"nisb":{"y":0},"nsigt":{"y":0},"nsiht":{"y":0},"nsis":{"w":-1,"h":-1},"nsib":{"y":0},"nsihb":{"y":0},"nsiho":0};				      // 栏目导航样式
var _navHidden =false;                                  //true:隐藏；flase：显示；           

var _headerTopStyleChanged = 0;
var _headerTopStyle = {"hts":-1,"hg":{"y":0,"r":3,"f":"","p":"","c":"#000"}};			  // 顶部样式（模板）
var _customHeaderTopStyle = {"hts":-1,"hg":{"y":0,"r":3,"f":"","p":"","c":"#000"}};  // 自定义顶部样式

var _memberTopBarChanged = 0;
var _memberTopBar = false;							// 顶部登录条

var _footerStyleChanged = 0;
var _footerStyleData = {"fa":0,"fh":-1,"f":{"t":0,"l":0,"r":0,"u":0,"d":0},"rt":{"y":0,"f":"","s":-1,"c":""},"gt":{"y":0,"f":"","s":-1,"w":0,"d":0,"c":""},"ht":{"c":""},"fb":{"y":0}};				// 底部导航样式（模板）
var _customFooterStyleData = {"fa":0,"fh":-1,"f":{"t":0,"l":0,"r":0,"u":0,"d":0},"rt":{"y":0,"f":"","s":-1,"c":""},"gt":{"y":0,"f":"","s":-1,"w":0,"d":0,"c":""},"ht":{"c":""},"fb":{"y":0}};      // 自定义底部导航样式

var _useTemplateTitle = true;					// 是否使用模版
var _titleData = {"x":"变频器","h":false,"t":-1,"l":-1,"f":"","s":-1,"w":-1,"c":"","i":0,"u":0,"st":{"y":0,"x":"","f":"","c":"","s":0,"i":0,"w":0,"u":0},"jm":{"y":0},"fp":false};						// 当前页面的数据
var _templateTitleData = {"x":"变频器","h":false,"t":-1,"l":-1,"f":"","s":-1,"w":-1,"c":"","i":0,"u":0,"st":{"y":0,"x":"","f":"","c":"","s":0,"i":0,"w":0,"u":0},"jm":{"y":0},"fp":false};			// 模版的数据

var _useTemplateLocale = true;				// 是否使用模板
var _localeData = {"h":false,"x":"中文","y":"English","z":"繁体","v":"日本語","k":"한국의","lo":"ພາສາລາວ","th":"ภาษาไทย","es":"lengua española","ru":"русский","t":-1,"l":-1,"f":"","s":-1,"w":-1,"fp":false};						// 当前页面的数据
var _templateLocaleData = {"h":false,"x":"中文","y":"English","z":"繁体","v":"日本語","k":"한국의","lo":"ພາສາລາວ","th":"ภาษาไทย","es":"lengua española","ru":"русский","t":-1,"l":-1,"f":"","s":-1,"w":-1,"fp":false};		// 模板的数据

var _useTemplateLogo = true;					// 是否使用模版
var _logoData = {"i":"","h":true,"t":-1,"l":-1,"g":-1,"w":-1,"lt":0,"addr":"","sc":-1,"ot":1,"des":"","desd":false,"p":""};							// 当前页面的数据
var _templateLogoData = {"i":"","h":true,"t":-1,"l":-1,"g":-1,"w":-1,"lt":0,"addr":"","sc":-1,"ot":1,"des":"","desd":false,"p":""};			// 模版的数据

var _useTemplateBackground = true;		// 是否使用模版
var _backgroundData = {"styleDefault":true,"s":true,"h":false,"r":3,"o":"","sw":-1,"e":0,"wbh":-1,"id":"","p":"","bBg":{"y":0,"r":3,"f":"","p":"","c":"#000"},"cBg":{"y":0,"r":3,"f":"","p":"","c":"#000"}};				// 当前页面的数据
var _templateBackgroundData = {"styleDefault":true,"s":true,"h":false,"r":3,"o":"","sw":-1,"e":0,"wbh":-1,"id":"","p":"","bBg":{"y":0,"r":3,"f":"","p":"","c":"#000"},"cBg":{"y":0,"r":3,"f":"","p":"","c":"#000"}};// 模版的数据
var _customBackgroundData = {"styleDefault":true,"s":true,"h":false,"r":3,"o":"","sw":-1,"e":0,"wbh":-1,"id":"","p":"","bBg":{"y":0,"r":3,"f":"","p":"","c":"#000"},"cBg":{"y":0,"r":3,"f":"","p":"","c":"#000"}};          //自定义的数据

var _useTemplateBrowserTitle = true;							// 是否使用全局模版
var _browserTitleData = "变频器";					// 当前页面的数据
var _browserTitleExt = "";						// 当前页面的标题扩展
var _templateBrowserTitleData = "变频器";		// 模版的数据
var _pageBrowserTitleData = "变频器";					// 当前页面的自定义数据（页面独立样式设置）
var _isNewsAndProBrowserTitle = false;
var _companyName = "变频器";   							// 企业名称

var _useTemplateSearchKeyword = true;
var _searchKeywordData = "变频器控";
var _templateSearchKeywordData = "变频器";
var _pageSearchKeywordData = "";

var _useTemplateSearchDesc = true;
var _searchDescData = "变频器";
var _templateSearchDescData = "变频器";
var _pageSearchDescData = "";

var _delModuleIdList = [];				// 用于管理模式下记录删除的模块列表,保存后生效

var _advanceSettingData = {};				// 用于存放高级设置的高级功能和高级功能数据
var _businessSettingData = {};				// 用于存放高级设置的高级功能和商务功能数据
var _baiduBaseSetting = {};                   //用于存放百度优化里的优化数据；

var _siteBulletinOemVersion = "";
var _topBarMyNewsVersion = "00e890e8c338ddc288bbb3f9b335aefb";
var _topBarUpdateVersion = "5751fbc6eb0818de7e66ac32f9d4739f";
var _panelOptionData = {"sysIndex":2,"schemeCust":false,"webDebug":false,"allowedStyle":true,"templateType_free":0,"imgMode":2,"fontFamilyList":[{"first":"SimSun","second":"宋体"},{"first":"PMingLiU","second":"新细明体"},{"first":"FangSong_GB2312","second":"仿宋_GB2312"},{"first":"微软雅黑","second":"微软雅黑"},{"first":"隶书","second":"隶书"},{"first":"幼圆","second":"幼圆"},{"first":"STKaiti","second":"华文楷体"},{"first":"方正舒体","second":"方正舒体"},{"first":"华文彩云","second":"华文彩云"},{"first":"华文行楷","second":"华文行楷"},{"first":"华文宋体","second":"华文宋体"},{"first":"楷体,楷体_GB2312, SimKai","second":"楷体"},{"first":"黑体, SimHei","second":"黑体"},{"first":"Arial","second":"Arial"},{"first":"Tahoma","second":"Tahoma"},{"first":"impact","second":"impact"},{"first":"Arial Serif","second":"Arial Serif"},{"first":"Georgia","second":"Georgia"},{"first":"Garamond","second":"Garamond"},{"first":"Times New Roman","second":"Times New Roman"},{"first":"andale mono","second":"andale mono"},{"first":"arial black,avant garde","second":"arial black"},{"first":"comic sans ms","second":"comic sans ms"}]}



var _aid = 4723;