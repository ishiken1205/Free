function setParam(param){
	 const form = document.getElementById('calcForm');
     const hidParam = document.getElementById('hid_param');
     hidParam.value = param;
     form.submit();
}