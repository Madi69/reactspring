import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class EventUser extends Component {

  constructor(props) {
    super(props);
    this.state = {
        event: {}, 
        users:[], 
        isLoading: true
    };
  }
  
  async componentDidMount() {
    const anEvent = await (await fetch(`/api/event/${this.props.match.params.id}`)).json();
    const theUsers = await (await fetch(`/api/users`)).json();
    this.setState({event: anEvent});
    this.setState({users: theUsers});
    this.setState({isLoading:false});
    //console.log(JSON.stringify(anEvent.attendees));
  }

  check(event){
    console.log(event.target.name);
  }
  render() {
    const {event, isLoading} = this.state;
    const usersHere = this.state.users;
    if (isLoading) {
      return <p>Loading...</p>;
    }
    
    //check if the username already exists in the attandees
    const userList = usersHere.map(user => {
      var found = false;
      for(var i=0;i<this.state.event.attendees.length;i++){
        if(this.state.event.attendees[i].name==user.name){
          found = true;
        }
      }
      return <tr key={user.name}>
        <td style={{whiteSpace: 'nowrap'}}>{user.name}</td>
        <td>
          
          <input type="checkbox" name={user.name} checked={found} onChange={this.check}>
          </input>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <h3>Registration for event {this.state.event.title} on {this.state.event.date} </h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="30%">Booked</th>
            </tr>
            </thead>
            <tbody>
            {userList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}
      

export default EventUser;