module.exports = {
    outputDir: "../src/main/resources/static",
    indexPath: "../static/index.html",
    devServer: {
        proxy: {
            '/api' : {
                "target": "http://localhost:9000/"
            }
        }
    }
};