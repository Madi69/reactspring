//https://github.com/oktadeveloper/okta-spring-boot-react-crud-example
import React, { Component } from 'react';
import './App.css';
import GroupEdit from './GroupEdit';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GroupList from './GroupList';
import UserList from './UserList';
import EventList from './EventList';
import UserEdit from './UserEdit';
import EventEdit from './EventEdit';
import EventUser from './EventUser';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/groups' exact={true} component={GroupList}/>
          <Route path='/users' exact={true} component={UserList}/>
          <Route path='/events' exact={true} component={EventList}/>
          <Route path='/users/:id' component={UserEdit}/>
          <Route path='/groups/:id' component={GroupEdit}/>
          <Route path='/events/:id' component={EventEdit}/>
          <Route path='/eventusers/:id' component={EventUser}/>
        </Switch>
      </Router>
    )
  }
}

export default App;
