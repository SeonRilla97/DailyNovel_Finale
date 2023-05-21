import { defineStore } from "pinia";

export const useDisplayCategoryStore = defineStore("displayCategory", {
    state: () => ({
        category: '1'
    }),
    actions: {
        saveCategory(String) {
            this.category = String;
        },
    }
});
