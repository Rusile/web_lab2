<%
    String[] messages = pageContext.getException().getMessage().split("\n");
    String exception = pageContext.getException().getClass().toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>
<h4>Type:</h4> <h5>--<%=  exception%></h5>

<h4>Message:</h4>
<h5>
<%
    for (int i = 0; i < messages.length; i++) {
        if (i != 0)
            out.print("<br>");
        out.print("--" + messages[i]);

    }
%>
</h5>
</body>
</html>