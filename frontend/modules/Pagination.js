import React from 'react'

export default React.createClass({            
    render() {
        var arr = [];
        var p = this.props.page*1;
        arr.push(<li><a href={this.props.url+1}>{'<<'}</a></li>);
        arr.push(<li><a href={p > 1 ? this.props.url+(p-1) : this.props.url+p}>{'<'}</a></li>);
        var shift = 0;
        if (p-2 < 1) {
            shift = 1 - (p-2)        
        } else if (p+2 > this.props.pages) {
            shift = this.props.pages - (p+2);
        }
        for (var i = p-2+shift; i <= Math.min(p+2+shift, this.props.pages); i++) {
            if (i>0) {
                arr.push(<li className={i == p ? 'active' : ''} ><a href={this.props.url+i}>{i}</a></li>);
            }
        }
        arr.push(<li><a href={p < this.props.pages ? this.props.url+(p+1) : this.props.url+p}>{'>'}</a></li>);
        arr.push(<li><a href={this.props.url+this.props.pages}>{'>>'}</a></li>);
        return <div className='center-block'>
            <ul className='pagination centered'>
                {arr}
            </ul>
        </div>
    }
});
