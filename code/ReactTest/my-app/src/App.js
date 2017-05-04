import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Clock from './Components/Clock';
import Toggle from './Components/Toggle';
import LoginControl from './Components/LoginControl';
import NameForm from './Components/NameForm';
import TextAreaForm from './Components/TextAreaForm';
import Dropdown from './Components/Dropdown';

const comment = {
    date: new Date(),
    text: 'I hope you enjoy learning React!',
    author: {
        name: 'Hello Kitty',
        avatarUrl: 'http://placekitten.com/g/64/64'
    }
};
const numbers = [1,2,3,4,5];
class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <LogoBunch/>
          <h2>Welcome to React</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
          <ToggleTest/>
          <Clock timing="1000"/>
          <Clock timing="10000"/>
          <LoginControl/>
          <Comment date={comment.date} text={comment.text} author={comment.author}/>
          <NumberList numbers={numbers}/>
          <NameForm/>
          <TextAreaForm/>
          <Dropdown/>
      </div>
    );
  }
}

function Comment(props) {
    return (
        <div className="Comment">
            <hr/>
            <UserInfo user={props.author}/>
            <div className="Comment-text">
                {props.text}
            </div>
            <div className="Comment-date">
                {formatDate(props.date)}
            </div>
        </div>
    );
}
function Avatar(props) {
    return (
        <img className="Avatar"
             src={props.user.avatarUrl}
             alt={props.user.name}
        />
    );
}
function UserInfo(props) {
    return (
        <div className="UserInfo">
            <Avatar user={props.user} />
            <div className="UserInfo-name">
                {props.user.name}
            </div>
        </div>
    );
}
function formatDate(date) {
    return date.toLocaleDateString();
}
function ToggleTest(props) {
    return (
        <div>
            <hr/>
            <h2>Try out these buttons made from 1 component</h2>
            <Toggle/><Toggle/><Toggle/><Toggle/><Toggle/>
        </div>
    );
}
function LogoBunch(props) {
    return (
        <div>
            <img src={logo} className="App-logo" alt="logo" />
            <img src={logo} className="App-logo" alt="logo" />
            <img src={logo} className="App-logo" alt="logo" />
            <img src={logo} className="App-logo" alt="logo" />
            <img src={logo} className="App-logo" alt="logo" />
        </div>
    );
}
function NumberList(props) {
    const numbers = props.numbers;
    return (
        <ul>
            {numbers.map((number) =>
                <ListItem key={number.toString()}
                          value={number} />
            )}
        </ul>
    );
}
function ListItem(props) {
    // Correct! There is no need to specify the key here:
    return <li className="list">{props.value}</li>;
}

export default App;
