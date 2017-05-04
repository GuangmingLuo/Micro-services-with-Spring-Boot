/**
 * Created by lenna on 2017-05-04.
 * Self updating clock template
 */

import React,{Component} from "react";

class Clock extends Component {

    constructor(props) {
        super(props);
        this.state = {date: new Date(),timing:props.timing};
    }

    componentDidMount() {
        this.timerID = setInterval(() => this.tick(), this.state.timing); //implements custom function that ticks and is executed 1 time per second
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    render() {
        return (
            <div><hr/>
                <Welcome name="World"/>
                <p>This clock updates once in {this.state.timing/1000} second(s)</p>
                {/*<h2>It is {this.props.date.toLocaleTimeString()}.</h2>*/}
                <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
            </div>
        );
    }

    tick(){
        // use setState({}) instead of this.state.date = ...
        this.setState({
            date: new Date()
        });
        // Wrong, setState does not update synchronously
        // this.setState({
        //     counter: this.state.counter + this.props.increment,
        // });

        // Correct, Do this instead, setState will give you a prevState argument
        // this.setState(function(prevState, props) {
        //     return {
        //         counter: prevState.counter + props.increment
        //     };
        // });
    }



}
function Welcome(props) {
    return <h3>Hello, {props.name}</h3>;
}


export default Clock;