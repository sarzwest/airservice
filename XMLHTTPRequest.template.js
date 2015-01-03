var stateChanged = function () {
  if (this.readyState === 4) {
    console.log(this.responseText);
  }
};
document.cookie="c=kukina"
xmlhttp = new XMLHttpRequest();
xmlhttp.open('POST', 'http://papastrojwork.local:8080/airservice-1.0/rest/test/cookieparam', true);
xmlhttp.addEventListener('readystatechange', stateChanged.bind(xmlhttp));
xmlhttp.send();

