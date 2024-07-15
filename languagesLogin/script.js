function switchLanguage(language) {
    const translations = {
        'en': {
            'formTitle': 'LOGIN',
            'usernameLabel': 'Username:',
            'passwordLabel': 'Password:',
            'loginButton': 'Login'
        },
        'vi': {
            'formTitle': 'ĐĂNG NHẬP',
            'usernameLabel': 'Tên người dùng:',
            'passwordLabel': 'Mật khẩu:',
            'loginButton': 'Đăng nhập'
        }
    };

    document.getElementById('form-title').innerText = translations[language]['formTitle'];
    document.getElementById('username-label').innerText = translations[language]['usernameLabel'];
    document.getElementById('password-label').innerText = translations[language]['passwordLabel'];
    document.getElementById('login-button').innerText = translations[language]['loginButton'];
}

function showUserInfo() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    //const outputDiv = document.getElementById('output');
    //outputDiv.innerHTML = `<p>Username: ${username}</p><p>Password: ${password}</p>`;
    
    alert("Your user name is "+username+" and password is "+password);
}
