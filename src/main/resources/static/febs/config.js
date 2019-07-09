layui.define(function(exports) {
  exports('conf', {
    container: 'febs',
    containerBody: 'febs-body',
    v: '2.0',
    base: layui.cache.base,  //  ./febs/
    css: layui.cache.base + 'css/', //febs模板样式请求
    views: '/views/',  //视图前缀统一请求
    viewLoadBar: true,
    debug: layui.cache.debug,
    name: 'febs',
    entry: '/index',
    engine: '',
    eventName: 'febs-event',
    tableName: 'febs',
    requestUrl: './'
  })
});
