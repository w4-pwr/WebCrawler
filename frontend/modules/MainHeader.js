import React from 'react'

export default React.createClass({
    render() {
        window._MainHeader = this;
        return <header className="main-header">

            {/* Logo */}
            <a href="index2.html" className="logo">
                {/* mini logo for sidebar mini 50x50 pixels */}
                <span className="logo-mini"><b>W</b>Cr</span>
                {/* logo for regular state and mobile devices */}
                <span className="logo-lg"><b>Web</b>Crawler</span>
            </a>

            {/* Header Navbar */}
            <nav className="navbar navbar-static-top" role="navigation">
                {/* Sidebar toggle button*/}
                <a href="#" className="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span className="sr-only">Toggle navigation</span>
                </a>
                {/* Navbar Right Menu */}
                <div className="navbar-custom-menu">
                    {this.renderDropdowns()}
                </div>
            </nav>
        </header>
    },
    renderDropdowns() {
        var Token = localStorage.getItem('token');
        if (Token) {
            return <ul className="nav navbar-nav">
                <li className="dropdown user user-menu">
                    <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                        <span className="hidden-xs">Your profile</span>
                    </a>
                    <ul className="dropdown-menu">
                        <li className="user-header account">
                            {/*tutaj uzyc tokena*/}
                        </li>
                    </ul>
                </li>
            </ul>
        } else {
            return <ul className="nav navbar-nav">
                <li className="dropdown user user-menu">
                    {/* Menu Sign up */}

                    <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                        <span className="hidden-xs2">Sign up</span>
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
                                <a className="btn btn-default btn-flat" onClick={this.signUp}>Sign up</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <li className="dropdown user user-menu">
                    {/* Menu Sign in */}

                    <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                        <span className="hidden-xs">Sign in</span>
                    </a>
                    <ul className="dropdown-menu">
                        <li className="user-header sign-in">
                            <input id='email' className='form-control' type='text' placeholder='email' />
                            <input id='password' className='form-control' type='password' placeholder='password' />

                        </li>
                        <li className="user-footer">
                            <div className="pull-right">
                                <a className="btn btn-default btn-flat" onClick={this.signIn}>Sign in</a>
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
        var registrationDate= new Date().toJSON().slice(0,10);

        var xhttp = new XMLHttpRequest();

        function check() {
            if (password =="" || password2=="" || username =="" || firstname =="" || email=="" || lastname=="") {
                alert("Fill all spaces to register");
            }
            else {
                if (password.length > 7) {
                    if (password == password2) {
                        xhttp.onreadystatechange = function () {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                                console.log(xhttp.responseText);
                            }
                        };
                        var url = "localhost:8080/WebCrawler/registration";
                        var params = JSON.stringify({
                            email: email,
                            password: password,
                            username: username,
                            firstName: firstname,
                            lastName: lastname,
                            registrationDate: registrationDate,
                        })
                        xhttp.open('POST', url , true);
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
        }
        check();

    },
    signIn(e) {
        var $menu = $(e.target).closest('.dropdown-menu');
        var email = $menu.find('#email')[0].value;
        var password = $menu.find('#password')[0].value;
        console.log(email, password);
        var xhttp = new XMLHttpRequest();
        
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
            	var Token = xhttp.responseText;
            	console.log(xhttp.responseText);
            	if (Token) {
                	localStorage.setItem('token', Token);
                	this.forceUpdate();
                }
            }
        };
        xhttp.open('POST', 'signIn?email='+email+'&password='+password, true);
        xhttp.send();
    },
    signOut() {
    	if(localStorage.getItem('token') != null)
    		localStorage.removeItem('token');
    },
    componentDidMount() {
        $('.dropdown-menu .user-header').click(function(e) {
            e.stopPropagation();
        });
    }
});
