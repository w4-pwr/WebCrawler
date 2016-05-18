import React from 'react'
import { render } from 'react-dom'
import { Router, Route, hashHistory } from 'react-router'
import App from './modules/App'
import Searches from './modules/Searches'
import Results from './modules/Results'
import Profile from './modules/Profile'

render((
  <Router history={hashHistory}>
    <Route component={App}>
        <Route path="/" components={{main: Searches}} />
        <Route path="/s/:page" components={{main: Searches}} />
        <Route path="/search/:id" components={{main: Results}} />
        <Route path="/search/:id/:page" components={{main: Results}} />
        <Route path="/profile" components={{main: Profile}} />
    </Route>
  </Router>
), document.getElementById('app'))
