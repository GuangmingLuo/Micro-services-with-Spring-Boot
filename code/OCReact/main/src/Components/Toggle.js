/**
 * Created by lenna on 2017-05-04.
 * Toggle component that shows or hides on click
 */
import React, {Component} from "react";

class Toggle extends Component {
    constructor(props) {
        super(props);
        this.state = {isToggleOn: true,body:props.body,label:props.label};

        // This binding is necessary to make `this` work in the callback
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        // uses arrowfunction to get prevState from this.state and toggles it.
        this.setState(prevState => ({
            isToggleOn: !prevState.isToggleOn
        }));
    }

    render() {
        return (
            <button onClick={this.handleClick}>
                {this.state.isToggleOn ? this.state.label : this.state.body}
            </button>
        );
    }
}

export default Toggle;
