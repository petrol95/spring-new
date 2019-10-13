<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <form:form action="processForm" modelAttribute="student">
            First Name: <form:input path="firstName" />
            <br>
            Last Name: <form:input path="lastName" />
            <br>
            Country:
            <form:select path="country">
                <form:option value="Russia" label="Russia"/>
                <form:option value="France" label="France"/>
            </form:select>
            <br>
            Java <form:checkbox path="programmingLanguages" value="Java"/>
            C++ <form:checkbox path="programmingLanguages" value="C++"/>
            PHP <form:checkbox path="programmingLanguages" value="PHP"/>
            <br>
            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>