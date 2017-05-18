function validate() {
    var check = document.getElementById('orderForm').checkValidity();
    if(check){
        document.getElementById('orderForm').submit();
    }else {
        document.getElementById('table_error').innerHTML="The table id must be filled in!";
    }
}
