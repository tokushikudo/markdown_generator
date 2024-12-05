document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");

    // 業務外で取り組んでいること
    const outsideWorkContainer = document.getElementById("outside-work-container");
    const addOutsideWorkBtn = document.getElementById("add-outside-work-btn");
    const MAX_OUTSIDE_WORK = 3;

    const createOutsideWorkTemplate = () => {
        const div = document.createElement("div");
        div.className = "outside-work-group";
        div.innerHTML = `
            <label>タイトル</label>
            <input type="text" name="outsideWorkTitles[]" placeholder="例: オープンソース活動" required>
            <label>内容</label>
            <textarea name="outsideWorkContents[]" rows="4" placeholder="具体的な内容を入力してください" required></textarea>
            <button type="button" class="remove-btn">削除</button>
        `;
        return div;
    };

    addOutsideWorkBtn.addEventListener("click", () => {
        const currentCount = outsideWorkContainer.querySelectorAll(".outside-work-group").length;

        if (currentCount < MAX_OUTSIDE_WORK) {
            const newOutsideWork = createOutsideWorkTemplate();
            outsideWorkContainer.appendChild(newOutsideWork);
        } else {
            alert("業務外活動は最大3つまでしか登録できません。");
        }
    });

    outsideWorkContainer.addEventListener("click", (event) => {
        if (event.target.classList.contains("remove-btn")) {
            const workGroup = event.target.closest(".outside-work-group");
            outsideWorkContainer.removeChild(workGroup);
        }
    });

    // 資格
    const qualificationsContainer = document.getElementById("qualifications-container");
    const addQualificationBtn = document.getElementById("add-qualification-btn");
    const createQualificationTemplate = () => {
        const div = document.createElement("div");
        div.className = "qualification-group";
        div.innerHTML = `
            <label for="qualification-name">資格名</label>
            <input type="text" name="qualificationNames[]" placeholder="例: 基本情報技術者試験" required>
            <label for="qualification-year">取得年</label>
            <select name="qualificationYears[]" required>
                <option value="" disabled selected>年を選択</option>
                ${Array.from({ length: 30 }, (_, i) => {
                  const year = new Date().getFullYear() - i;
                  return `<option value="${year}">${year}</option>`;
                }).join("")}
            </select>
            <label for="qualification-month">取得月</label>
            <select name="qualificationMonths[]" required>
                <option value="" disabled selected>月を選択</option>
                ${Array.from({ length: 12 }, (_, i) => `<option value="${i + 1}">${i + 1}</option>`).join("")}
            </select>
            <button type="button" class="remove-btn">削除</button>
        `;
        return div;
    };

    addQualificationBtn.addEventListener("click", () => {
        const newQualification = createQualificationTemplate();
        qualificationsContainer.appendChild(newQualification);
    });

    qualificationsContainer.addEventListener("click", (event) => {
        if (event.target.classList.contains("remove-btn")) {
            const qualificationGroup = event.target.closest(".qualification-group");
            qualificationsContainer.removeChild(qualificationGroup);
        }
    });
});
