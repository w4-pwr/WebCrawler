import React from 'react'
import MainHeader from './MainHeader'

export default React.createClass({
    render() {
        return <div className="wrapper hold-transition skin-blue sidebar-mini">
        <div className="wrapper">
            <MainHeader app={this} />
            
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
                    <li><a href="#">Link in level aaa2</a></li>
                    <li><a href="#">Link in level 2</a></li>
                  </ul>
                </li>
              </ul>
              {/* /.sidebar-menu */}
            </section>
            {/* /.sidebar */}
            </aside>
            
            {this.props.main}

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
