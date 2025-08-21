let currentPage = 0;
let pageSize = 5;
let currentSortBy = "id";
let currentSortDir = "asc";
function loadGoods(page) {
currentPage = page;
        fetch(`/good/data?page=${page}&size=${pageSize}&sortBy=${currentSortBy}&sortDir=${currentSortDir}`)
            .then(res => res.json())
            .then(data => {
                // Update table body
                const tbody = document.querySelector("#dataTable tbody");
                tbody.innerHTML = "";
                data.content.forEach((item, index) => {
                    tbody.innerHTML += `
                        <tr>
                            <td>${page * pageSize + index + 1}</td>
                            <td>${item.goodName}</td>
                            <td>${item.quantity ?? ""}</td>
                            <td>${item.kg ?? ""}</td>
                            <td>${item.category.categoryName}</td>
                             <td>
                                            <a href="/good/edit/${item.id}" class="text-dark">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="/good/delete/${item.id}" class="text-dark">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </td>
                        </tr>
                    `;
                });

                // Update pagination
                const pagination = document.getElementById("pagination");
                pagination.innerHTML = "";
                const prevBtn = document.createElement("button");
                prevBtn.classList.add("me-2");
                prevBtn.innerHTML =`<i class="fa-solid fa-angles-left"></i>`;
                prevBtn.disabled = page === 0;
                prevBtn.onclick = () => loadGoods(page-1);
                pagination.appendChild(prevBtn);
                for (let i = 0; i < data.totalPages; i++) {
                    const btn = document.createElement("button");
                    btn.innerText = i + 1;
                    btn.onclick = () => {
                        currentPage = i;
                        loadGoods(i);
                    };
                    if (i === page) {
                    btn.disabled = true;
                    btn.classList.add("bg-dark","text-white","me-1")
                    }
                    pagination.appendChild(btn);
                }
                const nextBtn = document.createElement("button");
                nextBtn.classList.add("ms-2")
                                nextBtn.innerHTML =`<i class="fa-solid fa-angles-right"></i>`;
                                nextBtn.disabled = page === data.totalPages - 1;
                                nextBtn.onclick = () => loadGoods(page+1);
                                pagination.appendChild(nextBtn);
            });
    }
        function sortTable(column) {
            if (currentSortBy === column) {
                // toggle asc/desc
                currentSortDir = currentSortDir === 'asc' ? 'desc' : 'asc';
            } else {
                currentSortBy = column;
                currentSortDir = 'asc';
            }
            loadGoods(currentPage);
        }

        function changePageSize() {
        pageSize = document.getElementById("pageSize").value;
        loadGoods(0);
        }
    document.addEventListener("DOMContentLoaded", () => {
    loadGoods(currentPage)
    let downloadPdfBtn = document.getElementById("downloadPdfBtn");
        if(downloadPdfBtn) {
        downloadPdfBtn.addEventListener("click", (e) => {
                e.preventDefault();
                const fileName = `goodList_${Date.now()}.pdf`;
                            fetch(`/good/download/pdf?page=${currentPage}&size=${pageSize}&sortBy=${currentSortBy}&sortDir=${currentSortDir}`)
                                .then(resp => resp.blob())
                                .then(blob => {
                                    const url = window.URL.createObjectURL(blob);
                                    const a = document.createElement('a');
                                    a.href = url;
                                    a.download = fileName;
                                    document.body.appendChild(a);
                                    a.click();
                                    a.remove();
                                    window.URL.revokeObjectURL(url);
                                });
                        });}
                        let downloadExcelBtn = document.getElementById("downloadExcelBtn");
                        if(downloadExcelBtn) {
                        downloadExcelBtn.addEventListener("click", (e) => {
                        e.preventDefault();
                        const fileName = `goodList_${Date.now()}.xlsx`;
                                                    fetch(`/good/download/excel?page=${currentPage}&size=${pageSize}&sortBy=${currentSortBy}&sortDir=${currentSortDir}`)
                                                        .then(resp => resp.blob())
                                                        .then(blob => {
                                                            const url = window.URL.createObjectURL(blob);
                                                            const a = document.createElement('a');
                                                            a.href = url;
                                                            a.download = fileName;
                                                            document.body.appendChild(a);
                                                            a.click();
                                                            a.remove();
                                                            window.URL.revokeObjectURL(url);
                                                        });
                                                });}
                        })