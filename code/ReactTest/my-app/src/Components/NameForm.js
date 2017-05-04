/**
 * Created by lenna on 2017-05-04.
 * Form handling component
 */
import React, {Component} from 'react';


class NameForm extends Component{
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value.toUpperCase()});
    }

    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return (
            <div>
                <span>Current text is:</span>
                <span>{this.state.value}</span>
                <p>This text is {this.state.value.length} characters long</p>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Name:
                        <input type="text" value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <input type="submit" value="Submit" />
                </form>
                <br/>
            </div>

        );
    }

}


export default NameForm;