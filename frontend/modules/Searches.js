import React from 'react'

export default React.createClass({
    componentWillMount() {
        this.data = {
            searches: [
                {
                    id: 'xxxxxx',
                    keyword: 'xxxx',
                    addedDate: '000'
                },{
                    id: 'xxxxxx',
                    keyword: 'xxxx',
                    addedDate: '000'
                },{
                    id: 'xxxxxx',
                    keyword: 'xxxx',
                    addedDate: '000'
                },{
                    id: 'xxxxxx',
                    keyword: 'xxxx',
                    addedDate: '000'
                }
            ],
            pages: 30
        };   
    },
    render() {
        if (!this.props.params.page) {
            this.props.params.page = 1;
        }
        return <div className='content-wrapper'>
            <section className='content-header'>
                <form action='#' method='get' className=''>
                    <div className='input-group form-group form-group-lg'>
                      <input type='text' name='q' className='form-control' placeholder='Add new search...' style={{backgroundImage: 'none', backgroundPosition: '0% 0%', backgroundRepeat: 'repeat'}} />
                      <span className='input-group-btn'>
                        <button type='submit' name='search' id='search-btn' className='btn btn-lg'><i className='fa fa-plus' />
                        </button>
                      </span>
                    </div>
                </form>
            </section>

            {/* Main content */}
            <section className='container container-fluid'>
                {this.renderRows()}
                <div className='center-block'>
                <ul className='pagination centered'>
                    {this.renderPagination()}
                </ul>
                </div>
            </section>
        </div>;
    },
    renderRows() {
        return this.data.searches.map((el)=>{
            return <div className='row'><div className='col-md-12'>{el.keyword}</div></div>;
        })
    },
    renderPagination() {
        var arr = [];
        var p = this.props.params.page*1;
        if (p > 1) {
            arr.push(<li><a href={'#/s/'+(p-1)}>{'<'}</a></li>)
        }
        for (var i = p-2; i <= Math.min(p+2, this.data.pages); i++) {
            if (i>0) {
                arr.push(<li className={i == p ? 'active' : ''} ><a href={'#/s/'+i}>{i}</a></li>)
            }
        }
        if (p < this.data.pages) {
            arr.push(<li><a href={'#/s/'+(p+1)}>{'>'}</a></li>)
        }
        return arr;
    }
});
