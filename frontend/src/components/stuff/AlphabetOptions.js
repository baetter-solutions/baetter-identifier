export const alphabetOptions = Array.from({ length: 26 }, (_, index) => {
    const letter = String.fromCharCode(65 + index);
    return { label: letter, value: index + 1 };
});