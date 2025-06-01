fetch('http://localhost:8080/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  credentials: 'include', // если используете куки для сессии
  body: JSON.stringify({ username, password }),
})
.then(res => {
  if (res.ok) {
    // логин успешен — например, редирект на главную
  } else {
    // показать ошибку
  }
});
