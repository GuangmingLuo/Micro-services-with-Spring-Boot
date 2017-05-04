/**
 * Created by lenna on 2017-05-04.
 * Dropdown/Select Component implementation
 */
import React, {Component} from 'react';

class Dropdown extends Component{
    constructor(props) {
        super(props);
        this.state = {value: 'coconut'};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('Your favorite flavor is: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return (
        <div>
            <br/>
            <form onSubmit={this.handleSubmit}>
                <label>
                    Select a Restaurant:
                    <select value={this.state.value} onChange={this.handleChange}>
                        <option value="starbucks">Starbucks</option>
                        <option value="burgerking">Burger King</option>
                        <option value="pizzahut">PizzaHut</option>
                        <option value="react">React</option>
                    </select>
                </label>
                <input type="submit" value="Submit" />
            </form>
            <br/>
        </div>

        );
    }
}

export default Dropdown;