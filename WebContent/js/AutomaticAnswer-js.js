$(".size").change(function(){
    alert(this.value);
});

function upValue(m,n) {
    var value = document.getElementsByClassName("value");
    var val = m + "&" + n +"&";
   for(var i = 0; i < value.length; i++){
       val += ($(value[i]).val()+";");
   }
   alert(val);
}