function switchR(rV) {
    document.querySelectorAll('#r').forEach(element => {
        element.innerHTML = rV.toString();
    })
    document.querySelectorAll('#-r').forEach(element => {
        element.innerHTML = (-rV).toString();
    })
    document.querySelectorAll('#r2').forEach(element => {
        element.innerHTML = (rV / 2).toString();
    })
    document.querySelectorAll('#-r2').forEach(element => {
        element.innerHTML = (-rV / 2).toString();
    })
}