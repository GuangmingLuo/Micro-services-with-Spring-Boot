/**
 * Created by lenna on 2017-05-04.
 * Warning component
 */

import React, {Component} from 'react';

class Warning extends Component{
    constructor(props) {
        super(props);
        this.state = {showWarning: true}
        this.handleToggleClick = this.handleToggleClick.bind(this);
    }

    handleToggleClick() {
        this.setState(prevState => ({
            showWarning: !prevState.showWarning
        }));
    }

    render() {
        return (
            <div>
                <hr/>
                <WarningBanner warn={this.state.showWarning} />
                <button onClick={this.handleToggleClick}>
                    {this.state.showWarning ? 'Hide' : 'Show'}
                </button>
            </div>
        );
    }


}

function WarningBanner(props) {
    if (!props.warn) {
        //will not return the rendering part of this component but will still call
        // componentWillUpdate and componentDidUpdate
        return null;
    }

    return (
        <div className="warning">
            Warning!
        </div>
    );
}

export default Warning;