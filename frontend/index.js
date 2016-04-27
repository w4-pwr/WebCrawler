import React from 'react'
import { render } from 'react-dom'
import { Router, Route, hashHistory } from 'react-router'
import Homepage from './modules/Homepage'

render((
  <Router history={hashHistory}>
    <Route path="/" component={Homepage}/>
    <Route path="/user" component={Homepage}/>
  </Router>
), document.getElementById('app'))
