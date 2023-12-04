(defn rolling-variance [window-size sequence]
  (let [window (atom [])]
    (map (fn [x]
           (swap! window conj x)
           (when (> (count @window) window-size)
             (swap! window #(subvec % 1))
             (let [mean (/ (apply + @window) (count @window))
                   squared-deviations (map #(* (- % mean) (- % mean)) @window)
                   variance (/ (apply + squared-deviations) (count @window))]
               (println "Current Variance:" variance)
               variance)))
         sequence)))

;; Пример использования:
(doseq [result (take 10 (rolling-variance 3 (range)))]
  (println "Final Variance:" result))
