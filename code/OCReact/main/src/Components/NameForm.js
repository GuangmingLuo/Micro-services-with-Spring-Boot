/**
 * Created by lenna on 2017-05-04.
 * Form handling component
 */
import React, {Component} from 'react';


class NameForm extends Component{
    constructor(props) {
        super(props);
        this.state = {username: '',password:''};


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });

        // this.setState({value: event.target.value.toUpperCase()});
    }

    handleSubmit(event) {
        alert('Submitted username: ' + this.state.username+" and password: "+ this.state.password);
        event.preventDefault();
    }

    render() {
        return (
            <div><hr/>
                <span>Current username is:</span>
                <span>{this.state.username}</span>
                <p>This username is {this.state.username.length} characters long</p>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Username:
                        <input name="username" type="text" value={this.state.username} onChange={this.handleChange} />
                    </label><br/>
                    <label>
                        Password:
                        <input name="password" type="password" value={this.state.password} onChange={this.handleChange} />
                    </label><br/>
                    <input type="submit" value="Submit" />
                </form>
                <br/>
                <hr/>
            </div>

        );
    }

}


export default NameForm;