function ajaxModule(url, method, data, callback){
    $.ajax({
        url: url,
        type : method,
        data: data,
        async: false,
        'success' : function(s){
            callback(s);
        },
        'error' : function(e) {
            callback(e);
        }
    })
}