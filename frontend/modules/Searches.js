import React from 'react'

export default React.createClass({
    componentWillMount() {
        this.data = {searches: [
            /*{
                id: 'xxxxxx',
                keyword: 'xxxx',
                addedDate: '000'
            },*/
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},
            {content: 'xxxxxx'},

        ]};   
    },
    render() {
        return <div className="content-wrapper">
            <section className="content-header">
                <form action="#" method="get" className="">
                    <div className="input-group form-group form-group-lg">
                      <input type="text" name="q" className="form-control" placeholder="Add new search..." style={{backgroundImage: 'none', backgroundPosition: '0% 0%', backgroundRepeat: 'repeat'}} />
                      <span className="input-group-btn">
                        <button type="submit" name="search" id="search-btn" className="btn btn-lg"><i className="fa fa-plus" />
                        </button>
                      </span>
                    </div>
                </form>
            </section>

            {/* Main content */}
            <section className="container container-fluid">
                {this.renderRows()}
                <div className="center-block">
                <ul className="pagination centered">
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                </ul>
                </div>
            </section>
        </div>;
    },
    renderRows() {
        return this.data.searches.map((el)=>{
            return <div className="row"><div className="col-md-12">{el.content}</div></div>;
        })
    }
});
