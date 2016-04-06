import React from 'react'

export default React.createClass({
    render() {
        return (<header className="main-header">
            
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
                <ul className="nav navbar-nav">
                  {/* Messages: style can be found in dropdown.less*/}
                  
                  {/* User Account Menu */}
                  <li className="dropdown user user-menu">
                    {/* Menu Toggle Button */}
                    {/*<a href="#" className="dropdown-toggle" data-toggle="dropdown">
                      <img src="dist/img/boxed-bg.jpg" className="user-image" alt="User Image" />
                      <span className="hidden-xs">Alexander Pierce</span>
                    </a>
                    <ul className="dropdown-menu">
                      <li className="user-header">
                        <img src="dist/img/boxed-bg.jpg" className="img-circle" alt="User Image" />
                        <p>Alexander Pierce</p>
                      </li>
                      <li className="user-footer">
                        <div className="pull-left">
                          <a href="#" className="btn btn-default btn-flat">Profile</a>
                        </div>
                        <div className="pull-right">
                          <a href="#" className="btn btn-default btn-flat">Sign out</a>
                        </div>
                      </li><button href="#" className="dropdown-toggle" data-toggle="dropdown" type="button">
                    </ul>*/}
                      <button href="#" className="btn btn-primary dropdown-toggle" data-toggle="dropdown" type="button">
                          <span className="hidden-xs">Sign up</span>
                      </button>
                      <ul className="dropdown-menu">
                          <li className="user-header">
                              
                              <input id='username' className='form-control' type='text' placeholder='username' />
                              <input id='email' className='form-control' type='text' placeholder='email' />
                              <input id='firstname' className='form-control' type='text' placeholder='first name' />
                              <input id='lastname' className='form-control' type='text' placeholder='last name' />
                              <input id='password' className='form-control' type='password' placeholder='password' />
                              <input id='password2' className='form-control' type='password' placeholder='re-type password' />
                              <p class="titlearea win-type-ellipsis" id="title">
                                <span class="pagetitle" >
                                    Hesdgfsdfllo
                                </span>
                              </p>
                          </li>
                          <li className="user-footer">

                              <div className="pull-right">
                                  <a className="btn btn-default btn-flat" onClick={this.signUp}>Sign up</a>
                              </div>
                          </li>
                      </ul>
                  </li>
                </ul>
              </div>
            </nav>
            </header>);
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
        console.log(registrationDate);

        var xhttp = new XMLHttpRequest();
        function check() {
            if(password =="" || password2=="" || username =="" || firstname =="" || email==""){
                alert("Fill all spaces to register");
            }
            if (password == password2) {
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        console.log(xhttp.responseText);
                    }
                };
                xhttp.open('POST', 'signUp?email=' + email + '&password=' + password + '&username=' + username + '&firstname=' + firstname + '&lastname=' + lastname, true);
                xhttp.send();
            }
            else {

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
                console.log(xhttp.responseText);
            }
        };
        xhttp.open('GET', 'signIn?email='+email+'&password='+password, true);
        xhttp.send();
    },
    componentDidMount() {
        $('.dropdown-menu .user-header').click(function(e) {
            e.stopPropagation();
        });
    }
});
