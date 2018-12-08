define(['Ajax','moment'],function (Ajax,moment) {
    var cn=moment(new Date()).locale('zh-cn');

    for(var i=0;i<15;i++){
        console.log(cn.get('date'),cn.format('dddd'));
        cn.add(1,'days');
    }
    cn=moment(new Date()).locale('zh-cn');
    console.log(cn.format("LLLL"));
    // console.log(cn.format('dddd'));
});
