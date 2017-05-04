/**
 * Created by lenna on 2017-05-04.
 * Main toggle to switch between testing and REST client
 */

import React, {Component} from "react";
import Clock from './Clock';
import Toggle from './Toggle';
import LoginControl from './LoginControl';
import NameForm from './NameForm';
import TextAreaForm from './TextAreaForm';
import Dropdown from './Dropdown';

const comment = {
    date: new Date(),
    text: 'I hope you enjoy learning React!',
    author: {
        name: 'Hello Kitty',
        avatarUrl: 'http://placekitten.com/g/64/64'
    }
};
const numbers = [1,2,3,4,5];

class TestingControl extends Component{
    constructor(props) {
        super(props);
        this.handleTestClick = this.handleTestClick.bind(this);
        this.handleRestClick = this.handleRestClick.bind(this);
        this.state = {isTesting: false};
    }
    handleTestClick() {
        this.setState({isTesting: true});
    }

    handleRestClick() {
        this.setState({isTesting: false});
    }



    render(){
        const isTesting = this.state.isTesting;
        let content = null;
        if (isTesting) {
            content = (
                <div>
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
        } else {
            content = (
                <div>
                    <h1>REST interface: WIP</h1>
                </div>
            );
        }


        return(
            <div>
                <button disabled={this.state.isTesting} onClick={this.handleTestClick}>Test Mode</button>
                <button disabled={!this.state.isTesting} onClick={this.handleRestClick}>REST Mode</button>
                <hr/>
                {content}

            </div>
        );
    }


}

function ToggleTest(props) {
    return (
        <div>
            <h2>Try out these buttons made from 1 component</h2>
            <Toggle/><Toggle/><Toggle/><Toggle/><Toggle/>
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

export default TestingControl;