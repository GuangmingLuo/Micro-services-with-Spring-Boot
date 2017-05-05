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
import axios from 'axios';

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
        this.handleGetClick = this.handleGetClick.bind(this);
        this.handleClearClick = this.handleClearClick.bind(this);
        this.state = {isTesting: false,response:'',hasReceived:false};



    }
    handleTestClick() {
        this.setState({isTesting: true});
    }

    handleRestClick() {
        this.setState({isTesting: false});
    }
    handleClearClick() {
        this.setState({hasReceived: false});
    }

    handleGetClick() {
        let url = "http://localhost/api/menu?restaurantId=1";
        let self = this;
        axios.get(url).then(function(result) {
            // we got it!
            // axios also returns other header information
            // we can make the distinction by using .data or .status, etc...
            self.setState({response:result.data});
            self.setState({hasReceived:true});
            // data = result.data;
            // alert(JSON.stringify(result));
        }).catch(function(error){
            console.log(error.description);
        });

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

            if(this.state.hasReceived){
                content = (
                    <div>
                        <h1>REST interface: WIP</h1>
                        <h2>Requesting menu from Restaurant with id=1</h2>
                        <Toggle label="Show JSON Response" body={JSON.stringify(this.state.response)}/>
                        <h2>There are {this.state.response.length} menus:</h2>
                        <h3>{JSON.stringify(this.state.response[0].name)}, has {this.state.response[0].foods.length} items:</h3>
                        <h4>{JSON.stringify(this.state.response[0].foods[0].name)}</h4>
                        <h4>{JSON.stringify(this.state.response[0].foods[1].name)}</h4>
                        <h4>{JSON.stringify(this.state.response[0].foods[2].name)}</h4>
                        <h3>{JSON.stringify(this.state.response[1].name)}, has {this.state.response[1].foods.length} items:</h3>
                        <h4>{JSON.stringify(this.state.response[1].foods[0].name)}</h4>
                        <h4>{JSON.stringify(this.state.response[1].foods[1].name)}</h4>
                        <h4>{JSON.stringify(this.state.response[1].foods[2].name)}</h4>
                        {/*<h3>{JSON.stringify(data[0])}</h3>*/}
                    </div>
                );
            }else{
                content = (
                    <div>
                        <h1>REST interface: WIP</h1>

                    </div>
                );
            }

        }


        return(
            <div>
                <button disabled={this.state.isTesting} onClick={this.handleTestClick}>Test Mode</button>
                <button disabled={!this.state.isTesting} onClick={this.handleRestClick}>REST Mode</button>
                <button disabled={this.state.isTesting} onClick={this.handleGetClick}>Do GET request</button>
                <button disabled={this.state.isTesting} onClick={this.handleClearClick}>Clear request</button>
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
            <Toggle label="ON" body="OFF"/><Toggle label="ON" body="OFF"/>
            <Toggle label="ON" body="OFF"/><Toggle label="ON" body="OFF"/>
            <Toggle label="ON" body="OFF"/><Toggle label="ON" body="OFF"/>
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