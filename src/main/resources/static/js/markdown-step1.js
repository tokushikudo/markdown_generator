document.addEventListener("DOMContentLoaded", () => {
    const initialDataScript = document.getElementById("initial-data");
    
    // エスケープされたHTMLエンティティをデコード
    const parser = new DOMParser();
    const decodedText = parser.parseFromString(initialDataScript.textContent, "text/html").documentElement.textContent;

    // JSON.parseでデコード後のテキストを解析
    const initialData = decodedText ? JSON.parse(decodedText) : {};
    console.log(initialData); // デバッグ用：初期データを確認

    const form = document.getElementById("form");
    const skillFields = [
        { select: "languages", other: "language-other", label: "言語", list: "languages-list", name: "languages[]" },
        { select: "frameworks", other: "framework-other", label: "フレームワーク", list: "frameworks-list", name: "frameworks[]" },
        { select: "libraries", other: "library-other", label: "ライブラリ", list: "libraries-list", name: "libraries[]" },
        { select: "os-software", other: "os-software-other", label: "OS・ソフトウェア", list: "os-software-list", name: "osSoftware[]" }
    ];

    // 初期化処理：スキル項目の初期値設定
    skillFields.forEach(({ select, list, name }) => {
        const selectElement = document.getElementById(select);
        const listContainer = document.getElementById(list);

        // 初期値の取得（JSONから対応する値を取得）
        const initialValues = initialData[select] || []; // 該当データが無ければ空配列

        // 初期値を各スキルリストに追加
        initialValues.forEach(value => {
            const option = Array.from(selectElement.options).find(opt => opt.value === value); // セレクトオプションを検索
            const text = option ? option.text : value; // オプションが無い場合は値をそのまま表示
            const skillDiv = createSkillElement(value, text, list, name);
            listContainer.appendChild(skillDiv);

            // セレクトボックス内の該当オプションを選択状態に設定
            if (option) option.selected = true;
        });
    });


    const appealsContainer = document.getElementById("appeals-container");
    const addAppealBtn = document.getElementById("add-appeal-btn");

    // 最大アピールポイント数
    const MAX_APPEALS = 3;

    // アピールポイントテンプレート生成
    const createAppealTemplate = () => {
        const div = document.createElement("div");
        div.className = "appeal-group";
        div.innerHTML = `
            <label>タイトル</label>
            <input type="text" name="titles[]" placeholder="例: 向上心" required>
            <label>内容</label>
            <textarea name="contents[]" rows="4" placeholder="具体的な内容を入力してください" required></textarea>
            <button type="button" class="remove-btn">削除</button>
        `;
        return div;
    };

    // アピールポイント追加処理
    addAppealBtn.addEventListener("click", () => {
        const appealCount = appealsContainer.querySelectorAll(".appeal-group").length;

        if (appealCount < MAX_APPEALS) {
            const newAppeal = createAppealTemplate();
            appealsContainer.appendChild(newAppeal);
        } else {
            alert("アピールポイントは最大3つまでしか登録できません。");
        }
    });

    // アピールポイント削除処理
    appealsContainer.addEventListener("click", (event) => {
        if (event.target.classList.contains("remove-btn")) {
            const appealGroup = event.target.closest(".appeal-group");
            appealsContainer.removeChild(appealGroup);
        }
    });

    // スキル入力処理
    skillFields.forEach(({ select, other, list, name }) => {
        const selectElement = document.getElementById(select);
        const otherInput = document.getElementById(other);
        const listContainer = document.getElementById(list);

        // セレクトボックスの変更イベント
        selectElement.addEventListener("change", () => {
            const selectedOptions = Array.from(selectElement.selectedOptions);

            selectedOptions.forEach(option => {
                if (option.value && !document.querySelector(`[data-value="${option.value}"][data-list="${list}"]`)) {
                    const skillDiv = createSkillElement(option.value, option.text, list, name);
                    listContainer.appendChild(skillDiv);
                    option.selected = false; // 選択を解除
                }
            });
        });

        // その他の入力を登録
        const addButton = document.getElementById(`add-${other}`);
        addButton.addEventListener("click", () => {
            const value = otherInput.value.trim();
            if (value && !document.querySelector(`[data-value="${value}"][data-list="${list}"]`)) {
                const skillDiv = createSkillElement(value, value, list, name);
                listContainer.appendChild(skillDiv);
                otherInput.value = ""; // 入力欄をクリア
            } else if (!value) {
                alert("入力欄が空です。値を入力してください。");
            } else {
                alert("既に登録されています。");
            }
        });

        // Enterキーで送信を防止
        otherInput.addEventListener("keypress", (event) => {
            if (event.key === "Enter") {
                event.preventDefault(); // フォーム送信を防止
                addButton.click(); // 「登録」ボタンをクリック
            }
        });
    });

    // スキル項目を作成する関数
    function createSkillElement(value, text, list, name) {
        const skillDiv = document.createElement("div");
        skillDiv.className = "skill-item";
        skillDiv.setAttribute("data-value", value);
        skillDiv.setAttribute("data-list", list);

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


    // スキル入力がnullでないかをチェックする関数
    function validateSkills() {
        const skillFields = [
            { list: "languages-list", label: "言語" },
            { list: "frameworks-list", label: "フレームワーク" },
            { list: "libraries-list", label: "ライブラリ" },
            { list: "os-software-list", label: "OS・ソフトウェア" }
        ];

        const missingSkills = skillFields.filter(({ list }) => {
            const listContainer = document.getElementById(list);
            if (!listContainer) {
                return true; // 見つからない場合、未入力扱い
            }
            return listContainer.children.length === 1;
        });

        if (missingSkills.length > 0) {
            const missingLabels = missingSkills.map(({ label }) => label).join("、");
            alert(`以下のスキル項目が選択されていません：${missingLabels}`);
            return false; // フォーム送信を中止
        }

        return true; // すべての項目が入力されている
    }

    // フォーム送信時にスキル入力を検証
    form.addEventListener("submit", (event) => {
        if (!validateSkills()) {
            event.preventDefault(); // フォーム送信を中止
        }
    });

});