import React from 'react'

export default React.createClass({
    render() {
        window._MainHeader = this;
        return <header className='main-header'>

            {/* Logo */}
            <a href="#" className="logo">
                {/* mini logo for sidebar mini 50x50 pixels */}
                <span className='logo-mini'><b>W</b>Cr</span>
                {/* logo for regular state and mobile devices */}
                <span className='logo-lg'><b>Web</b>Crawler</span>
            </a>

            {/* Header Navbar */}
            <nav className='navbar navbar-static-top' role='navigation'>
                {/* Sidebar toggle button*/}
                <a href='#' className='sidebar-toggle' data-toggle='offcanvas' role='button'>
                    <span className='sr-only'>Toggle navigation</span>
                </a>
                {/*<form action='#' method='get' className='sidebar-form'>
                    <div className='input-group'>
                      <input type='text' name='q' className='form-control' placeholder='Search...' style={{backgroundImage: 'none', backgroundPosition: '0% 0%', backgroundRepeat: 'repeat'}} />
                      <span className='input-group-btn'>
                        <button type='submit' name='search' id='search-btn' className='btn btn-flat'><i className='fa fa-search' />
                        </button>
                      </span>
                    </div>
                </form>*/}


                {/* Navbar Right Menu */}
                <div className='navbar-custom-menu'>
                    {this.renderDropdowns()}
                </div>
            </nav>
        </header>
    },
    renderDropdowns() {
        var Token = localStorage.getItem('token');
        if (Token) {
            /*return <ul className='nav navbar-nav'>
                <li className='dropdown user user-menu'>
                    <a href='#' className='dropdown-toggle' data-toggle='dropdown'>
                        <span className='hidden-xs'>Your profile</span>
                    </a>
                    <ul className='dropdown-menu'>
                        <li className='user-header account'>
                            <div>{this.data.user.firstName} {this.data.user.lastName}, {this.data.user.username}</div>
                            <div>{this.data.user.email}</div>
                            {(()=>{
                                if (this.data.user.role == 'ADMIN') {
                                    return <div>ADMINISTRATOR</div>                                
                                } else {
                                    return null;
                                }
                            })()}
                        </li>
                    </ul>*/
            return <ul className="nav navbar-nav">
                <li className="dropdown user user-menu">
                    <a href="#/profile" className="dropdown-toggle">
                        <span className="hidden-xs">Your profile</span>
                    </a>
                </li>
                <li className="dropdown user user-menu">
                {/* Menu Sign up */}

                <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                    <span className="hidden-xs2">Edit profile</span>
                </a>
                <ul className="dropdown-menu">
                    <li className="user-header sign-up">
                        <input id='username' className='form-control' type='text' placeholder='username' />
                        <input id='email' className='form-control' type='text' placeholder='email' />
                        <input id='firstname' className='form-control' type='text' placeholder='first name' />
                        <input id='lastname' className='form-control' type='text' placeholder='last name' />
                        <input id='password' className='form-control' type='password' placeholder='password' />
                        <input id='password2' className='form-control' type='password' placeholder='re-type password' />
                    </li>
                    <li className="user-footer">

                        <div className="pull-right">
                            <a className="btn btn-default btn-flat" onClick={this.editProfile}>Edit profile</a>
                        </div>
                    </li>
                </ul>
            </li>
                <li>
                    <a href='#' className='dropdown-toggle' onClick={this.signOut}>
                        <span className='hidden-xs'>Sign out</span>
                    </a>
                </li>
                
                
            </ul>
        } else {
            return <ul className='nav navbar-nav'>
                <li className='dropdown user user-menu'>
                    {/* Menu Sign up */}

                    <a href='#' className='dropdown-toggle' data-toggle='dropdown'>
                        <span className='hidden-xs2'>Sign up</span>
                    </a>
                    <ul className='dropdown-menu'>
                        <li className='user-header sign-up'>
                            <input id='username' className='form-control' type='text' placeholder='username' />
                            <input id='email' className='form-control' type='text' placeholder='email' />
                            <input id='firstname' className='form-control' type='text' placeholder='first name' />
                            <input id='lastname' className='form-control' type='text' placeholder='last name' />
                            <input id='password' className='form-control' type='password' placeholder='password' />
                            <input id='password2' className='form-control' type='password' placeholder='re-type password' />
                        </li>
                        <li className='user-footer'>

                            <div className='pull-right'>
                                <a className='btn btn-default btn-flat' onClick={this.signUp}>Sign up</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <li className='dropdown user user-menu'>
                    {/* Menu Sign in */}

                    <a href='#' className='dropdown-toggle' data-toggle='dropdown'>
                        <span className='hidden-xs'>Sign in</span>
                    </a>
                    <ul className='dropdown-menu'>
                        <li className='user-header sign-in'>
                            <input id='email' className='form-control' type='text' placeholder='email' />
                            <input id='password' className='form-control' type='password' placeholder='password' />

                        </li>
                        <li className='user-footer'>
                            <div className='pull-right'>
                                <a className='btn btn-default btn-flat' onClick={this.signIn}>Sign in</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        }
    },
    signUp(e) {
        var $menu = $(e.target).closest('.dropdown-menu');
        var email = $menu.find('#email')[0].value;
        var password = $menu.find('#password')[0].value;
        var password2 = $menu.find('#password2')[0].value;
        var username = $menu.find('#username')[0].value;
        var firstname = $menu.find('#firstname')[0].value;
        var lastname = $menu.find('#lastname')[0].value;
        //var registrationDate= new Date().toJSON().slice(0,10);

        var xhttp = new XMLHttpRequest();

        if (!(password && password2 && username && firstname && email && lastname)) {
            alert('Fill all spaces to register');
        } else {
            if (password.length > 7) {
                if (password == password2) {
                    /*xhttp.onreadystatechange = function () {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            console.log(xhttp.responseText);
                        }
                    };*/
                    var params = JSON.stringify({
                        email: email,
                        password: password,
                        username: username,
                        firstName: firstname,
                        lastName: lastname
                    })
                    $.ajax({
                        type: 'PUT',
                        url: backendUrl + 'user',
                        crossorigin: true,
                        data: params
                    });
                    //xhttp.open('PUT', backendUrl + 'user' , true);
                    //xhttp.send(params);
                } else {
                    alert('Passwords don\'t match, Fill them correctly.');
                }
            } else {
                alert ('Password must be at least 8 characters long');
            }
        }

    },
    signIn(e) {
        var $menu = $(e.target).closest('.dropdown-menu');
        var email = $menu.find('#email')[0].value;
        var password = $menu.find('#password')[0].value;
        $.post(backendUrl + 'login?email=' + email + '&password=' + password).done(function(data){
           	localStorage.setItem('token', data);
        });
        /*console.log(email, password);
        var xhttp = new XMLHttpRequest();
        
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
            	var Token = xhttp.responseText;
            	console.log(xhttp.responseText);
            	if (Token) {
                	localStorage.setItem('token', Token);
                }
            }
        };
        xhttp.open('POST', backendUrl + 'login?email='+email+'&password='+password, true);
        xhttp.send();*/
    },
    signOut() {
    	if (localStorage.getItem('token') != null) {
    		localStorage.removeItem('token');
        }
    },
    componentDidMount() {
        $('.dropdown-menu .user-header').click(function(e) {
            e.stopPropagation();
        });
    },
    editProfile(e) {
    	// tutaj trzeba pobrać dane użytkownika i 
    	
    	var $menu = $(e.target).closest('.dropdown-menu');
        var email = $menu.find('#email')[0].value;
        var password = $menu.find('#password')[0].value;
        var password2 = $menu.find('#password2')[0].value;
        var username = $menu.find('#username')[0].value;
        var firstname = $menu.find('#firstname')[0].value;
        var lastname = $menu.find('#lastname')[0].value;

        var xhttp = new XMLHttpRequest();

        function check() {
                if (password.length > 7) {
                    if (password == password2) {
                        xhttp.onreadystatechange = function () {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                                console.log(xhttp.responseText);
                            }
                        };
                        var params = JSON.stringify({
                            email: email,
                            password: password,
                            username: username,
                            firstName: firstname,
                            lastName: lastname,
                            registrationDate: registrationDate,
                        })
                        xhttp.open('POST', 'registration' , true);
                        xhttp.send(params);
                    }
                    else {
                        alert("Passwords not match, Fill them correctly.");
                    }
                }
                else {
                    alert ("Password must be at least 8 characters long");
                }
        }
        check();
    }
});
