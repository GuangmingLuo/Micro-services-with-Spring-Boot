/**
 * Created by lenna on 2017-05-04.
 * Login control system component
 */
import React, {Component} from "react";
import Warning from "./Warning";

const messages = ['React', 'Re: React', 'Re:Re: React'];


class LoginControl extends Component{
    constructor(props) {
        super(props);
        this.handleLoginClick = this.handleLoginClick.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
        this.state = {isLoggedIn: false};
    }

    handleLoginClick() {
        this.setState({isLoggedIn: true});
    }

    handleLogoutClick() {
        this.setState({isLoggedIn: false});
    }

    render() {
        const isLoggedIn = this.state.isLoggedIn;

        let button = null;
        if (isLoggedIn) {
            button = (
                <div>
                    <Mailbox unreadMessages={messages} />
                    <LogoutButton onClick={this.handleLogoutClick} />
                    <Warning/>
                </div>
            );
        } else {
            button = <LoginButton onClick={this.handleLoginClick} />;
        }

        return (
            <div>
                <hr/>
                <Greeting isLoggedIn={isLoggedIn} />
                {/*shows previously defined button element*/}
                {button}

            </div>
        );
    }

}

function UserGreeting(props) {
    return <h1>Welcome back!</h1>;
}
function GuestGreeting(props) {
    return <h1>Please Log in.</h1>;
}
function Greeting(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
        return <UserGreeting />;
    }
    return <GuestGreeting />;
}
function LoginButton(props) {
    return (
        <button onClick={props.onClick}>
            Login
        </button>
    );
}
function LogoutButton(props) {
    return (
        <button onClick={props.onClick}>
            Logout
        </button>
    );
}
function Mailbox(props) {
    const unreadMessages = props.unreadMessages;
    return (
        <div>
            {unreadMessages.length > 0 &&
            <h2>
                You have {unreadMessages.length} unread messages.
            </h2>
            }
        </div>
    );
}

export default LoginControl;