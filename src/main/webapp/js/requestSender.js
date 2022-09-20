$('form').on('submit', function (e) {
    e.preventDefault();

    if (validateAllValues()) {
        send(controller.xValue, controller.yValue, controller.rValue);
    }
    return false;
})


function send(x, y, r) {
    console.log("Sending request");
    document.getElementById('x_error').innerHTML = "";
    document.getElementById('y_error').innerHTML = "";
    document.getElementById('r_error').innerHTML = "";
    $.ajax({
        type: "GET",
        url: "./check-values",
        dataType: "html",
        data: "x=" + x +
            "&y=" + y +
            "&r=" + r,
        beforeSend: function () {
            $('submit').disabled = true;
        },
        error: function (jqXHR, ex) {
            let text = jqXHR.responseText;
            let el = document.createElement('html');
            el.innerHTML = text;
            let htmlElements = el.getElementsByTagName('h5');
            var stringWithHtmlTobeSavedInTextArea = "";
            for (i = 0; i < htmlElements.length; i++) {
                stringWithHtmlTobeSavedInTextArea += htmlElements[i].outerHTML;
            }
            alert(stringWithHtmlTobeSavedInTextArea.replaceAll("<h5>", "")
                .replaceAll("</h5>", ""));
        },
        success: function (data) {
            $('submit').disabled = false;
            addData(data);
        },
    });
}

