document.getElementById('getMyInfo').addEventListener('click', getMyInfo);

    document.getElementById('getData').addEventListener('click', getData);

    document
      .getElementById('login')
      .addEventListener('click', function (event) {
        event.preventDefault();
        getToken(
          document.getElementById('account').value,
          document.getElementById('pwd').value
        );
      });

    document.getElementById('logout').addEventListener('click', logout);

    if (sessionStorage.getItem('kosmoJwt')) {
      getMyInfo();
    }

    var account = document.getElementById('account').value;
    var pwd = document.getElementById('pwd').value;

    function getToken(account, pwd) {
      var jsonAccount = account;
      var jsonPwd = pwd;

      document.getElementById('account').value = '';
      document.getElementById('pwd').value = '';

      var loginInfo = JSON.stringify({
        account: jsonAccount,
        pwd: jsonPwd,
      });

      fetch('http://121.88.86.169:8484/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: loginInfo,
      })
        .then((res) => res.json())
        .then((data) => {
          sessionStorage.setItem('kosmoJwt', data.token);
          getMyInfo();
        });
    }

    function getData() {
      fetch('http://121.88.86.169:8484/api/products', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + sessionStorage.getItem('kosmoJwt'),
        },
      })
        .then((res) => res.json())
        .then((data) => {
          buildTable(data);
        });
    }

    function logout() {
      sessionStorage.clear();
      document.getElementById('loginForm').style.visibility = 'visible';
      document.getElementById('logout').style.visibility = 'hidden';
      document.getElementById('output').innerHTML = '';
    }

    function getMyInfo() {
      var output = document.getElementById('output');

      var result = parseJwt(sessionStorage.getItem('kosmoJwt'));
      if (result != undefined)
        document.getElementById('loginForm').style.visibility = 'hidden';

      var row = `<p>${JSON.stringify(result)}</p>`;
      output.innerHTML += row;
      document.getElementById('logout').style.visibility = 'visible';
    }

    function parseJwt(token) {
      var base64Url = token.split('.')[1]; //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
      var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      var jsonPayload = decodeURIComponent(
        atob(base64)
          .split('')
          .map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
          })
          .join('')
      );
      return JSON.parse(jsonPayload);
    }

    function buildTable(data) {
      var table = document.getElementById('myTable');
      console.log(data.length);
      for (var i = 0; i < data.length; i++) {
        var row = ` <tr>
                          <td>${data[i].name}</td>
                          <td>${data[i].description}</td>
                          <td>${data[i].price}</td>
                          <td><img src='images/product${data[i].productId}.PNG'></td>
                      </tr> `;
        table.innerHTML += row;
      }
    }