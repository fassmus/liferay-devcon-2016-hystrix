<%@ include file="/hystrix-demo-portlet/init.jsp" %>

<%
	HystrixDemoConfiguration configuration = (HystrixDemoConfiguration) GetterUtil.getObject(
	    renderRequest.getAttribute(HystrixDemoConfiguration.class.getName()));
	
	boolean useHystrix = configuration.userHystrix();
%>

<p>
	<img src="http://vignette2.wikia.nocookie.net/uncyclopedia/images/d/dc/Chuck-norris-002.jpg/revision/latest/scale-to-width-down/200?cb=20080312162141">
	<b><%=useHystrix ? HystrixDemoLocalServiceUtil.getChuckNorrisFactViaHystrix() : HystrixDemoLocalServiceUtil.getChuckNorrisFactViaDirectCall()%></b>
</p>