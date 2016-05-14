<%
  //Set the theme.
  String pageTheme=request.getParameter("theme");
  String currentTheme=null;
  if(pageTheme!=null && !pageTheme.trim().equals("")){
	  Object obj=request.getSession().getAttribute("currentTheme");
	  if(obj==null || !obj.toString().equals(pageTheme)){
		  request.getSession().setAttribute("currentTheme", pageTheme);
	  }
	  currentTheme=pageTheme;
  }
  else{
	  currentTheme=request.getSession().getAttribute("currentTheme")==null 
			  ? (request.getSession().getAttribute("theme")==null ? "default" : request.getSession().getAttribute("theme").toString())
			  : request.getSession().getAttribute("currentTheme").toString();
  }
  request.setAttribute("theme", currentTheme);
%>
