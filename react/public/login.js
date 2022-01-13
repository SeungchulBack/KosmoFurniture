$(function () {
  getData();
  getMyInfo();
});

let loginUser;

$('#login').click(function (event) {
  event.preventDefault();
  getToken($('#account').val(), $('#pwd').val());
});

$('#logout').click(logout);

var account = $('#account').val();
var pwd = $('#pwd').val();

function getToken(account, pwd) {
  var jsonAccount = account;
  var jsonPwd = pwd;

  $('#account').val('');
  $('#pwd').val('');

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
      loginUser = parseJwt(sessionStorage.getItem('kosmoJwt'));
      getMyInfo();
    });
}

function getData() {
  fetch('http://121.88.86.169:8484/api/products', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  })
    .then((res) => res.json())
    .then((data) => buildProducts(data));
}

function logout() {
  sessionStorage.clear();
  $('#login_button').css('visibility', 'visible');
  $('#logout').css('visibility', 'hidden');
  $('#welcome').empty();
}

function getMyInfo() {
  if (sessionStorage.getItem('kosmoJwt') != null) {
    var loginUser = parseJwt(sessionStorage.getItem('kosmoJwt'));
    $('#login_button').css('visibility', 'hidden');
    $('#logout').css('visibility', 'visible');
    $('#welcome').append(`<span>환영합니다 ${loginUser.fullName}님!</span>`);
  }
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

var productId;

function addCart(productId) {
  fetch('http://121.88.86.169:8484/api/products/cart?productId=' + productId, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + sessionStorage.getItem('kosmoJwt'),
    },
  }).then((res) => alert('cartAdded'));
}

function buildProducts(data) {
  var container = document.getElementById('pro-container');
  console.log(data.length);
  for (var i = 0; i < data.length; i++) {
    console.log(data[i].productId);
    console.log(data[i].productImage);

    var fileName =
      data[i].productImage == null ? '' : data[i].productImage.dbFileName;
    var row = ` <div class="pro">
          <img src="http://121.88.86.169:8484/files/${fileName}" alt="" />
          <div class="info">
            <h5>${data[i].name}</h5>
            <div class="star">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
            </div>
            <h6>${data[i].price}원</h6>
          </div>
          <a href="#" onclick="addCart(${data[i].productId}); return false;"
            ><i class="fas fa-shopping-bag cart"></i
          ></a>
        </div>`;
    container.innerHTML += row;
  }
}
function moveToCart() {
  window.location.href = `http://121.88.86.169:8484/shop/cart?token=${sessionStorage.getItem(
    'kosmoJwt'
  )}`;
}
