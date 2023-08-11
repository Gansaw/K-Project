import React, { useState } from 'react';
import Header from './Header';

const InsertNotice = () => {
    const [title, setTitle] = useState('');
    const [writer, setWriter] = useState('');
    const [content, setContent] = useState('');
    const [file, setFile] = useState(null);

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    };

    const handleWriterChange = (e) => {
        setWriter(e.target.value);
    };

    const handleContentChange = (e) => {
        setContent(e.target.value);
    };

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        const formData = new FormData();
        formData.append('title', title);
        formData.append('writer', writer);
        formData.append('content', content);
        formData.append('file', file);
        
    };

    return (
        <div>
            <Header />
            <h3>공지사항 등록</h3>
            <form onSubmit={handleSubmit}>
                <table>
                    <tr>
                        <td>제목</td>
                        <td>
                            <input type="text" name="title" value={title} onChange={handleTitleChange} />
                        </td>
                    </tr>
                    <tr>
                        <td>작성자</td>
                        <td>
                            <input type="text" name="writer" value={writer} onChange={handleWriterChange} />
                        </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" value={content} onChange={handleContentChange} cols="40" rows="10"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>첨부파일</td>
                        <td>
                            <input type="file" name="file" onChange={handleFileChange} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="새글 등록" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    );
};

export default InsertNotice;
