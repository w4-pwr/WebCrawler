var Homepage = React.createClass({
    render() {
        return <div className="hold-transition skin-blue sidebar-mini">
        <div className="wrapper">
            <MainHeader />
            
            {/* Left side column. contains the logo and sidebar */}
            <aside className="main-sidebar">

            {/* sidebar: style can be found in sidebar.less */}
            <section className="sidebar">

              {/* Sidebar Menu */}
              <ul className="sidebar-menu">
                <li className="header">HEADER</li>
                {/* Optionally, you can add icons to the links */}
                <li className="active"><a href="#"><i className="fa fa-link"></i> <span>Link</span></a></li>
                <li><a href="#"><i className="fa fa-link"></i> <span>Another Link</span></a></li>
                <li className="treeview">
                  <a href="#"><i className="fa fa-link"></i> <span>Multilevel</span> <i className="fa fa-angle-left pull-right"></i></a>
                  <ul className="treeview-menu">
                    <li><a href="#">Link in level 2</a></li>
                    <li><a href="#">Link in level 2</a></li>
                  </ul>
                </li>
              </ul>
              {/* /.sidebar-menu */}
            </section>
            {/* /.sidebar */}
            </aside>

            {/* Content Wrapper. Contains page content */}
            <div className="content-wrapper">
            {/* Content Header (Page header) */}
            <section className="content-header">
              <h1>
                Page Header
                <small>Optional description</small>
              </h1>
            </section>

            {/* Main content */}
            <section className="content">

              {/* Your Page Content Here */}

            </section>
            {/* /.content */}
            </div>
            {/* /.content-wrapper */}

            {/* Main Footer */}
            <footer className="main-footer">
            {/* To the right */}
            <div className="pull-right hidden-xs">
              Anything you want
            </div>
            {/* Default to the left */}
            <strong>Copyright &copy; 2015 <a href="#">Company</a>.</strong> All rights reserved.
            </footer>

            {/* Control Sidebar */}
            <aside className="control-sidebar control-sidebar-dark">
            {/* Create the tabs */}
            <ul className="nav nav-tabs nav-justified control-sidebar-tabs">
              <li className="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i className="fa fa-home"></i></a></li>
              <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i className="fa fa-gears"></i></a></li>
            </ul>
            {/* Tab panes */}
            <div className="tab-content">
              {/* Home tab content */}
              <div className="tab-pane active" id="control-sidebar-home-tab">
                <h3 className="control-sidebar-heading">Recent Activity</h3>
                <ul className="control-sidebar-menu">
                  <li>
                    <a href="javascript::;">
                      <i className="menu-icon fa fa-birthday-cake bg-red"></i>

                      <div className="menu-info">
                        <h4 className="control-sidebar-subheading">Langdon's Birthday</h4>

                        <p>Will be 23 on April 24th</p>
                      </div>
                    </a>
                  </li>
                </ul>
                {/* /.control-sidebar-menu */}

                <h3 className="control-sidebar-heading">Tasks Progress</h3>
                <ul className="control-sidebar-menu">
                  <li>
                    <a href="javascript::;">
                      <h4 className="control-sidebar-subheading">
                        Custom Template Design
                        <span className="label label-danger pull-right">70%</span>
                      </h4>

                      <div className="progress progress-xxs">
                        <div className="progress-bar progress-bar-danger" style={{width: '70%'}}></div>
                      </div>
                    </a>
                  </li>
                </ul>
                {/* /.control-sidebar-menu */}

              </div>
              {/* /.tab-pane */}
              {/* Stats tab content */}
              <div className="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
              {/* /.tab-pane */}
              {/* Settings tab content */}
              <div className="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                  <h3 className="control-sidebar-heading">General Settings</h3>

                  <div className="form-group">
                    <label className="control-sidebar-subheading">
                      Report panel usage
                      <input type="checkbox" className="pull-right" checked />
                    </label>

                    <p>
                      Some information about this general settings option
                    </p>
                  </div>
                  {/* /.form-group */}
                </form>
              </div>
              {/* /.tab-pane */}
            </div>
            </aside>
            {/* /.control-sidebar */}
            {/* Add the sidebar's background. This div must be placed
               immediately after the control sidebar */}
            <div className="control-sidebar-bg"></div>

        </div>
        </div>;
    }
});
var MainHeader = React.createClass({
    render() {
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
            </header>
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


ReactDOM.render(
    <Homepage />,
    document.getElementById('content')
);
