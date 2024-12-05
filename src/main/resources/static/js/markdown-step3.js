document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");
    const projectsContainer = document.getElementById("projects-container");
    const addProjectBtn = document.getElementById("add-project-btn");

    // スキルフィールド定義（1ページ目と同じ構造）
    const skillFields = [
        { select: ".languages", other: ".language-other", list: ".languages-list", name: "languages[]" },
        { select: ".frameworks", other: ".framework-other", list: ".frameworks-list", name: "frameworks[]" },
        { select: ".libraries", other: ".library-other", list: ".libraries-list", name: "libraries[]" },
        { select: ".os-software", other: ".os-software-other", list: ".os-software-list", name: "osSoftware[]" },
        { select: ".phases", other: null, list: ".phases-list", name: "phases[]" }
    ];

    // プロジェクトテンプレート生成
    const createProjectTemplate = () => {
        const div = document.createElement("div");
        div.className = "project-group";
        div.innerHTML = `
            <label for="project-name">プロジェクト名</label>
            <input type="text" name="projectNames[]" placeholder="プロジェクト名を入力" required>

            <label for="start-month">開始月</label>
            <input type="month" name="startMonths[]" required>

            <label for="role">担当役割</label>
            <input type="text" name="roles[]" placeholder="例: リーダー" required>

            <label for="team-size">チーム人数</label>
            <input type="number" name="teamSizes[]" placeholder="チーム人数を入力" required>

            <label for="project-duration">期間</label>
            <input type="text" name="durations[]" placeholder="例: 6ヶ月" required>

            <label for="task-phase">担当工程</label>
            <div class="skill-group">
                <select class="phases" multiple>
                    <option value="要件定義">要件定義</option>
                    <option value="設計">設計</option>
                    <option value="実装">実装</option>
                    <option value="テスト">テスト</option>
                    <option value="運用・保守">運用・保守</option>
                </select>
                <div class="phases-list selected-skills">
                    <label>選択されている工程</label>
                </div>
            </div>

            <label for="overall-team-size">PJ全体人数</label>
            <input type="number" name="overallTeamSizes[]" placeholder="全体人数を入力" required>

            <!-- 言語 -->
            <div class="skill-group">
                <label for="languages">言語</label>
                <select class="languages" multiple>
                    <option value="Java">Java</option>
                    <option value="JavaScript">JavaScript</option>
                    <option value="Python">Python</option>
                    <option value="C#">C#</option>
                    <option value="Ruby">Ruby</option>
                </select>
                <input type="text" class="language-other hidden" placeholder="その他の言語を入力">
                <button type="button" class="add-other-btn">登録</button>
                <div class="languages-list selected-skills">
                    <label>選択されている言語</label>
                </div>
            </div>

            <!-- 他のスキルグループ（フレームワーク、ライブラリ、OS・ソフトウェア） -->
            <div class="skill-group">
                <label for="frameworks">フレームワーク</label>
                <select class="frameworks" multiple>
                    <option value="Spring">Spring</option>
                    <option value="Django">Django</option>
                    <option value="React">React</option>
                    <option value="Angular">Angular</option>
                    <option value="Vue.js">Vue.js</option>
                </select>
                <input type="text" class="framework-other hidden" placeholder="その他のフレームワークを入力">
                <button type="button" class="add-other-btn">登録</button>
                <div class="frameworks-list selected-skills">
                    <label>選択されているフレームワーク</label>
                </div>
            </div>

            <div class="skill-group">
                <label for="libraries">ライブラリ</label>
                <select class="libraries" multiple>
                    <option value="jQuery">jQuery</option>
                    <option value="Lodash">Lodash</option>
                    <option value="Axios">Axios</option>
                    <option value="Bootstrap">Bootstrap</option>
                </select>
                <input type="text" class="library-other hidden" placeholder="その他のライブラリを入力">
                <button type="button" class="add-other-btn">登録</button>
                <div class="libraries-list selected-skills">
                    <label>選択されているライブラリ</label>
                </div>
            </div>

            <div class="skill-group">
                <label for="os-software">OS・ソフトウェア</label>
                <select class="os-software" multiple>
                    <option value="Linux">Linux</option>
                    <option value="Windows">Windows</option>
                    <option value="MacOS">MacOS</option>
                    <option value="Docker">Docker</option>
                    <option value="Kubernetes">Kubernetes</option>
                </select>
                <input type="text" class="os-software-other hidden" placeholder="その他のOS・ソフトウェアを入力">
                <button type="button" class="add-other-btn">登録</button>
                <div class="os-software-list selected-skills">
                    <label>選択されているOS・ソフトウェア</label>
                </div>
            </div>

            <label for="project-summary">プロジェクト概要</label>
            <textarea name="summaries[]" rows="4" placeholder="プロジェクトの概要を記入" required></textarea>

            <label for="responsibilities">担当内容</label>
            <textarea name="responsibilities[]" rows="4" placeholder="担当した内容を記入" required></textarea>

            <label for="diagram">構成図アップロード</label>
            <input type="file" name="diagrams[]">
        `;
        return div;
    };

    // プロジェクト経歴追加処理
    addProjectBtn.addEventListener("click", () => {
        const newProject = createProjectTemplate();
        projectsContainer.appendChild(newProject);

        // 新規プロジェクトのスキル処理を追加
        initializeSkillFields(newProject);
    });

    // 初期ロード時に既存プロジェクトのスキル処理を設定
    initializeSkillFields(document);

    // スキル処理の初期化関数
    function initializeSkillFields(container) {
        skillFields.forEach(({ select, other, list, name }) => {
            const selectElements = container.querySelectorAll(select);
            const otherInputs = other ? container.querySelectorAll(other) : null;

            selectElements.forEach(selectElement => {
                const listContainer = selectElement.closest(".skill-group").querySelector(list);

                selectElement.addEventListener("change", () => {
                    const selectedOptions = Array.from(selectElement.selectedOptions);
                    selectedOptions.forEach(option => {
                        if (option.value && !listContainer.querySelector(`[data-value="${option.value}"]`)) {
                            const skillDiv = createSkillElement(option.value, option.text, listContainer, name);
                            listContainer.appendChild(skillDiv);
                        }
                    });
                });
            });

            if (otherInputs) {
                otherInputs.forEach(otherInput => {
                    const addButton = otherInput.closest(".skill-group").querySelector(".add-other-btn");
                    const listContainer = otherInput.closest(".skill-group").querySelector(list);

                    addButton.addEventListener("click", () => {
                        const value = otherInput.value.trim();
                        if (value && !listContainer.querySelector(`[data-value="${value}"]`)) {
                            const skillDiv = createSkillElement(value, value, listContainer, name);
                            listContainer.appendChild(skillDiv);
                            otherInput.value = ""; // 入力欄をクリア
                        } else if (!value) {
                            alert("入力欄が空です。値を入力してください。");
                        } else {
                            alert("既に登録されています。");
                        }
                    });
                });
            }
        });
    }

    // スキル項目を作成する関数
    function createSkillElement(value, text, list, name) {
        const skillDiv = document.createElement("div");
        skillDiv.className = "skill-item";
        skillDiv.setAttribute("data-value", value);

        const skillLabel = document.createElement("span");
        skillLabel.className = "skill-name";
        skillLabel.innerText = text;

        const hiddenInput = document.createElement("input");
        hiddenInput.type = "hidden";
        hiddenInput.name = name;
        hiddenInput.value = value;

        const removeBtn = document.createElement("button");
        removeBtn.className = "remove-btn";
        removeBtn.innerText = "削除";
        removeBtn.addEventListener("click", () => {
            skillDiv.remove();
        });

        skillDiv.appendChild(skillLabel);
        skillDiv.appendChild(hiddenInput);
        skillDiv.appendChild(removeBtn);
        return skillDiv;
    }
});
