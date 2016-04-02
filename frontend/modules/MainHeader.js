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
                      </li>
                    </ul>*/}
                    <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                      <span className="hidden-xs">Sign in</span>
                    </a>
                    <ul className="dropdown-menu">
                      <li className="user-header">
                        <input id='email' className='form-control' type='text' placeholder='email' />
                        <input id='password' className='form-control' type='text' placeholder='password' />
                      </li>
                      <li className="user-footer">
                        <div className="pull-left">
                          <a href="#" className="btn btn-default btn-flat">Sign up</a>
                        </div>
                        <div className="pull-right">
                          <a className="btn btn-default btn-flat" onClick={this.signIn}>Sign in</a>
                        </div>
                      </li>
                    </ul>
                  </li>
                </ul>
              </div>
            </nav>
            </header>);
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
