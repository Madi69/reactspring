import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class UserList extends Component {

  constructor(props) {
    super(props);
    this.state = {users: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});
    console.log("Readin from database");
    fetch('api/users')
      .then(response => response.json())
      .then(data => this.setState({users: data, isLoading: false}))
      .catch(rejected => {
        alert("Unable to connect the server, try later..")
      });
  }

  async remove(name) {
    await fetch(`/api/user/${name}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      //need to read from database because no id to compare
      fetch('api/users')
      .then(response => response.json())
      .then(data => this.setState({users: data, isLoading: false}));
      //let updatedUsers = [...this.state.users].filter(i => i.name !== name);
      //this.setState({users: updatedUsers});
    });
  }

  render() {
    const {users, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const UserList = users.map(user => {
      return <tr key={user.name}>
        <td style={{whiteSpace: 'nowrap'}}>{user.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{user.email}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/users/" + user.name}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(user.name)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/users/new">Add User</Button>
          </div>
          <h3>My JUG Tour</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="30%">Name</th>
              <th width="30%">Email</th>
            </tr>
            </thead>
            <tbody>
            {UserList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default UserList;