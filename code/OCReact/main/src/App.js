import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import TestingControl from './Components/TestingControl';


class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <LogoBunch/>
          <h2>Welcome to React</h2>
        </div>
        <p className="App-intro">
          To get started, select a mode.
        </p>
        <TestingControl/>
      </div>
    );
  }
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


export default App;
