//任务类别
const taskTypeList = [
    {
    value: "",
    label: 'ALL'
    },{
        value: 0,
        label: '计算'
    },{
        value: 1,
        label: '同步'
    },{
        value: 2,
        label: '监控'
    },{
        value: 4,
        label: '标签'
    },{
        value: 3,
        label: '无类型'
    }]
//核心编号
const coreNumList = [
    {
        value: "",
        label: '所有'
    },{
        value: 1,
        label: '核心1'
    },{
        value: 2,
        label: '核心2'
    },{
        value: 3,
        label: '核心3'
    },{
        value: 4,
        label: '核心4'
    },{
        value: 5,
        label: '核心5'
    },{
        value: -1,
        label: '全核心'
    },{
        value: -100,
        label: '港美股'
    },{
        value: 6,
        label: '两融'
    }]
//即时状态
const  immediateStatusList = [
    {
    value: "",
    label: 'ALL'
    },{
        value: 0,
        label: '完成初始'
    },{
        value: 20,
        label: '正在执行'
    },{
        value: 40,
        label: '全部成功'
    },{
        value: 80,
        label: '全部失败'
    },{
        value: 60,
        label: '部分失败'
    },{
        value: 100,
        label: '无需执行'
    },{
        value: 120,
        label: '加载失败'
    },{
        value: 140,
        label: '任务超时'
    }]
//支持业务
const supportedBizList = [
    {
        value: "",
        label: 'ALL'
    }, {
        value: "0",
        label: '账户分析'
    }, {
        value: "1",
        label: '消息中心'
    }]
//插件类型
const pluginTypeList = [
    {
        value: "",
        label: 'ALL'
    }, {
        value: 0,
        label: '本地插件'
    }, {
        value: 1,
        label: '远程插件'
    }, {
        value: 2,
        label: 'kettle插件'
    }]
//执行日期类型要求
const tradeExecuteList = [
    {
    value: "",
    label: 'ALL'
    }, {
        value: 0,
        label: '无要求'
    }, {
        value: 1,
        label: '交易日执行'
    }, {
        value: 2,
        label: '非交易日执行'
    }]
//数据源
const localReadDBList = [
    {value: -1,label: "无"},
    {value: 5,label: "ogg主库"},
    {value: 6,label: "ogg从库"},
    {value: 7,label: "开户库从库"},
    {value: 3,label: "choice库"},
    {value: 50,label: "柜台Run1"},
    {value: 51,label: "柜台Run2"},
    {value: 52,label: "柜台Run3"},
    {value: 53,label: "柜台Run4"},
    {value: 54,label: "柜台Run5"},
    {value: 55,label: "柜台His1"},
    {value: 56,label: "柜台His2"},
    {value: 57,label: "柜台His3"},
    {value: 58,label: "柜台His4"},
    {value: 59,label: "柜台His5"},
    {value: 4,label: "柜台CRM库"},
    {value: 0,label: "账户分析主库"}]
//数据地
const localWriteDBList = [
    {value:-1,label: "无"},
    {value:5,label: "ogg主库"},
    {value:6,label: "ogg从库"},
    {value:7,label: "开户库从库"},
    {value:3,label: "choice库"},
    {value:50,label: "柜台Run1"},
    {value:51,label: "柜台Run2"},
    {value:52,label: "柜台Run3"},
    {value:53,label: "柜台Run4"},
    {value:54,label: "柜台Run5"},
    {value:55,label: "柜台His1"},
    {value:56,label: "柜台His2"},
    {value:57,label: "柜台His3"},
    {value:58,label: "柜台His4"},
    {value:59,label: "柜台His5"},
    {value:4,label: "柜台CRM库"},
    {value:0,label: "账户分析主库"}]
//时间范围
const timeSlotsList = [
    {value:"W",label:"本周"},
    {value:"M",label:"本月"},
    {value:"PHY",label:"近半年"},
    {value:"P1Y",label:"近一年"},
    {value:"Y",label:"本年"},
    {value:"ALL",label:"至今"}]
//是否需要修复工具
const needToolList = [
    {value:0,label:"是"},
    {value:1,label:"否"},
]
//执行方式
const executeTypeList = [
    {value:0,label:"流水"},
    {value:20,label:"每日"},
]

const statusList = [
    {
        value: 1,
        label: 'ALL'
    }, {
        value: 2,
        label: '已修复'
    }, {
        value: 3,
        label: '未修复'
    }]

export default{
    taskTypeList,
    coreNumList,
    immediateStatusList,
    supportedBizList,
    pluginTypeList,
    tradeExecuteList,
    localReadDBList,
    localWriteDBList,
    timeSlotsList,
    needToolList,
    executeTypeList,
    statusList
}
