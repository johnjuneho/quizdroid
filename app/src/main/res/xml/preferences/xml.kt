<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
<EditTextPreference
android:key="pref_url"
android:title="Questions URL"
android:defaultValue="http://example.com/questions.json"
android:inputType="textUri" />

<EditTextPreference
android:key="pref_sync_frequency"
android:title="Sync Frequency (Minutes)"
android:defaultValue="1440"
android:inputType="number" />
</PreferenceScreen>