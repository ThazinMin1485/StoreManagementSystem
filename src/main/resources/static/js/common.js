let currentPage = 0;
let pageSize = 5;
let currentSortBy = "id";
let currentSortDir = "asc";
let currentConfig;
let currentKeyword = "";
function loadGoods(page) {
currentPage = page;
        fetch(`${currentConfig.endpoint}?page=${page}&size=${pageSize}&sortBy=${currentSortBy}&sortDir=${currentSortDir}&keyword=${currentKeyword}`)
            .then(res => res.json())
            .then(data => {
                // Update table body
                const tbody = document.querySelector("#dataTable tbody");
                tbody.innerHTML = "";
                data.content.forEach((item, index) => {
                    let row = `<tr><td>${page * pageSize + index + 1}</td>`;
                                        currentConfig.columns.forEach(col => {
                                            if (typeof col === "function") {
                                                row += `<td>${col(item)}</td>`;
                                            } else {
                                                let value = col.split('.').reduce((o, key) => o?.[key] ?? "", item);
                                                row += `<td>${value}</td>`;
                                            }
                                        });
                                        row += "</tr>";
                                        tbody.innerHTML += row;
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
                    btn.classList.add("bg-dark","text-white")
                    }
                    btn.classList.add("me-2")
                    pagination.appendChild(btn);
                }
                const nextBtn = document.createElement("button");
                nextBtn.classList.add("ms-2")
                                nextBtn.innerHTML =`<i class="fa-solid fa-angles-right"></i>`;
                                nextBtn.disabled = (page === data.totalPages - 1) || (data.totalPages === 0);
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

        function initTable(config) {
        currentConfig = config;
        }
    document.addEventListener("DOMContentLoaded", () => {
    let searchText = document.getElementById("searchInput");
    if(searchText) {
    searchText.addEventListener("keyup", function () {
                currentKeyword = this.value.toLowerCase();
                console.log("currentKeyword-->", currentKeyword);
                loadGoods(currentPage);
            });
            }
    setTimeout(() => {
            const successMsg = document.getElementById("success");
            const errorMsg = document.getElementById("error");

            if (successMsg) {
                successMsg.style.transition = "opacity 0.5s ease";
                successMsg.style.opacity = "0";
                setTimeout(() => successMsg.remove(), 500); // remove after fade
            }

            if (errorMsg) {
                errorMsg.style.transition = "opacity 0.5s ease";
                errorMsg.style.opacity = "0";
                setTimeout(() => errorMsg.remove(), 500);
            }
        }, 3000);
    loadGoods(currentPage)
    let downloadPdfBtn = document.getElementById("downloadPdfBtn");
        if(downloadPdfBtn) {
        downloadPdfBtn.addEventListener("click", (e) => {
                e.preventDefault();
                const fileName = `${currentConfig.fileName}_${Date.now()}.pdf`;
                            fetch(`${currentConfig.downloadPdfEndpoint}?page=${currentPage}&size=${pageSize}&sortBy=${currentSortBy}&sortDir=${currentSortDir}&keyword=${currentKeyword}`)
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
                        const fileName = `${currentConfig.fileName}_${Date.now()}.xlsx`;
                                                    fetch(`${currentConfig.downloadExcelEndpoint}?page=${currentPage}&size=${pageSize}&sortBy=${currentSortBy}&sortDir=${currentSortDir}&keyword=${currentKeyword}`)
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